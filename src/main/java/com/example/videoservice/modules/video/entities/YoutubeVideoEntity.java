package com.example.videoservice.modules.video.entities;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "youtube_video_info")
@Data
@EqualsAndHashCode(callSuper=true)
public class YoutubeVideoEntity extends BaseEntity{
    @Column(name = "video_id")
    private String videoId;

    @Column(name = "title")
    private String title;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "published_date")
    private Date publishedDate;

    @Column(name = "definition")
    private String videoDefinition;

    @Column(name = "duration")
    private String videoDuration;

    @Column(name = "caption")
    private String videoCaption;

    @Column(name = "projection")
    private String videoprojection;

    @Column(name = "country_restricted")
    private String countryRestricted;

    @Column(name = "keyword")
    private String keyword;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id",referencedColumnName = "channel_id")
    private YoutubeChannelEntity channelInfo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_stat_id",referencedColumnName = "video_id")
    private YoutubeVideoStatisticsEntity videoStatistics;

    public void setKeyword(String keyword){
        this.keyword = keyword;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setPublishedDate(Date publishedDate){
        this.publishedDate = publishedDate;
    }
    
    public void setVideoId(String videoId){
        this.videoId = videoId;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setThumbnailUrl(String thumbnailUrl){
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setChannelInfo(YoutubeChannelEntity channelInfo){
        this.channelInfo = channelInfo;
    }

    public void setVideoStatistics (YoutubeVideoStatisticsEntity videoStatistics){
        this.videoStatistics = videoStatistics;
    }

    public void setVideoDefinition (String videoDefinition){
        this.videoDefinition = videoDefinition;
    }

    public void setVideoCaption (String videoCaption){
        this.videoCaption = videoCaption;
    }

    public void setVideoprojection (String videoprojection){
        this.videoprojection = videoprojection;
    }

    public void setCountryRestricted (String countryRestricted){
        this.countryRestricted = countryRestricted;
    }
}
