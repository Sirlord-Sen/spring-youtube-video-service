package com.example.videoservice.modules.video.services;

public interface YoutubeApiService {
    String crawlYoutubeVideoInfo(String keyword, long pageToCrawl);
}
