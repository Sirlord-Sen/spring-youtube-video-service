package com.example.videoservice.modules.video.services;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.example.videoservice.modules.video.entities.CrawlingEntity;
import com.example.videoservice.modules.video.entities.YoutubeChannelEntity;
import com.example.videoservice.modules.video.entities.YoutubeVideoEntity;
import com.example.videoservice.modules.video.entities.YoutubeVideoStatisticsEntity;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequestInitializer;
import com.google.api.services.youtube.YouTube.Search;
import com.google.api.services.youtube.model.*;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class YoutubeApiServiceImpl implements YoutubeApiService{
    
    @Autowired
    private Environment env;

    private static final long NUMBER_OF_VIDEOS_RETURNED = 50;

    public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    public static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private YouTube youtube;

    private long count = 0;

    @Autowired 
    YoutubeChannelService youtubeChannelService;

    @Autowired
    YoutubeVideoService youtubeVideoService;

    @Autowired
    YoutubeVideoStatisticsService youtubeVideoStatisticsService;

    @Autowired
    CrawlingService crawlingService;

    @Override
    @Async("threadPoolTaskExecutor")
    public String crawlYoutubeVideoInfo(String keyword,long pageToCrawl) {
        getYoutubeVideoList(keyword,pageToCrawl);
        return "Youtube video information is loading...";
    }

    @Transactional
    public List<Object> getYoutubeVideoList(String queryTerm, long pageToCrawl){
        try{
            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException{
                } 
            })
            .setApplicationName("YoutubeVideo")
            .setYouTubeRequestInitializer(new YouTubeRequestInitializer(env.getProperty("youtube.apikey"))).build();

            Search.List search = youtube.search().list("id,snippet");

            search.setQ(queryTerm);
            search.setType("video");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

            for(int i = 0; i < pageToCrawl; i++){
                String pageToken = null;
                CrawlingEntity crawling = crawlingService.getBySearchKey(queryTerm);
                if(crawling != null  && crawling.getNextPageToken() != null){
                    pageToken = crawling.getNextPageToken();
                    count = crawling.getTotalCount();
                    crawling.setCurrentPageToken(null);
                } else if(crawling == null){
                    crawling = new CrawlingEntity();
                    count = 0;
                    crawling.setSearchKey(queryTerm);
                    crawling.setCurrentPageToken(null);
                }
                if(pageToken != null) search.setPageToken(pageToken);

                SearchListResponse searchListResponse = search.execute();
                List<SearchResult> searchResults = searchListResponse.getItems();

                if(searchResults != null) extractAndSave(searchResults.iterator(), queryTerm);

                crawling.setNextPageToken(searchListResponse.getNextPageToken());

                crawlingService.update(crawling);

                System.out.println("Next Page token : " + searchListResponse.getNextPageToken());
            
            }

        }
        catch(GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        }
        catch(IOException e){
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        }
        catch (Throwable t) {
            t.printStackTrace();
        }

        return null;
    }

    private void extractAndSave(Iterator<SearchResult> iteratorSearchResults, String query) throws IOException{

        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();

            System.out.println("Video number = " + count + " Inserting video Information " + singleVideo.getId().getVideoId());
            count++;
            YoutubeVideoEntity youTubeVideo = youtubeVideoService.getByVideoId(singleVideo.getId().getVideoId());

            if (youTubeVideo == null) {
                youTubeVideo = new YoutubeVideoEntity();
                ResourceId rId = singleVideo.getId();

                youTubeVideo.setKeyword(query);
                youTubeVideo.setDescription(singleVideo.getSnippet().getDescription());
                youTubeVideo.setPublishedDate(new Date(singleVideo.getSnippet().getPublishedAt().getValue()));

                if (rId.getKind().equals("youtube#video")) {
                    Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();
                    youTubeVideo.setVideoId(rId.getVideoId());
                    youTubeVideo.setTitle(singleVideo.getSnippet().getTitle());
                    youTubeVideo.setThumbnailUrl(thumbnail.getUrl());

                    youTubeVideo.setChannelInfo(getChannelDetailsById(singleVideo.getSnippet().getChannelId()));

                    youTubeVideo.setVideoStatistics(getVideosStatistics(rId.getVideoId()));
                }

                youtubeVideoService.save(youTubeVideo);
            } else {
                System.out.println("Video Already exists... ");
            }


        }
    }

    private YoutubeChannelEntity getChannelDetailsById(String channelId) throws IOException {
        YouTube.Channels.List channels = youtube.channels().list("snippet, statistics");

        YoutubeChannelEntity youtubeChannelInfo = new YoutubeChannelEntity();
        youtubeChannelInfo.setChannelId(channelId);
        channels.setId(channelId);
        ChannelListResponse channelResponse = channels.execute();
        Channel c = channelResponse.getItems().get(0);

        youtubeChannelInfo.setName(c.getSnippet().getTitle());
        youtubeChannelInfo.setSubscriptionCount(c.getStatistics().getSubscriberCount().longValue());

        YoutubeChannelEntity channelInfo = youtubeChannelService.getByChannelId(channelId);

        if (channelInfo == null) {
            youtubeChannelService.save(youtubeChannelInfo);
        } else {
            return channelInfo;
        }
        return youtubeChannelInfo;
    }


    public YoutubeVideoStatisticsEntity getVideosStatistics(String id) throws IOException {
        YouTube.Videos.List list = youtube.videos().list("statistics");
        list.setId(id);
        Video v = list.execute().getItems().get(0);

        YoutubeVideoStatisticsEntity statistics = new YoutubeVideoStatisticsEntity();

        statistics.setVideoId(id);
        statistics.setLikeCount(v.getStatistics().getLikeCount() !=null ? v.getStatistics().getLikeCount().longValue():0);
        statistics.setDislikeCount(v.getStatistics().getDislikeCount() != null ? v.getStatistics().getDislikeCount().longValue():0);
        statistics.setFavoriteCount(v.getStatistics().getFavoriteCount() != null ? v.getStatistics().getFavoriteCount().longValue():0);
        statistics.setCommentCount(v.getStatistics().getCommentCount() != null ? v.getStatistics().getCommentCount().longValue() : 0);
        statistics.setViewCount(v.getStatistics().getViewCount() != null ? v.getStatistics().getViewCount().longValue() : 0);


        youtubeVideoStatisticsService.save(statistics);

        return statistics;
    }


    public YoutubeVideoEntity getContentDetails(String id, YoutubeVideoEntity youTubeVideo) throws IOException {
        YouTube.Videos.List list = youtube.videos().list("contentDetails");
        list.setId(id);
        Video v = list.execute().getItems().get(0);
        youTubeVideo.setVideoDefinition(v.getContentDetails().getDefinition());
        youTubeVideo.setVideoCaption(v.getContentDetails().getCaption());
        youTubeVideo.setVideoProjection(v.getContentDetails().getProjection());
        youTubeVideo.setCountryRestricted(v.getContentDetails().getCountryRestriction().toPrettyString());

        return youTubeVideo;
    }

}
