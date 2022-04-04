package com.example.videoservice.modules.video.services;

import java.util.List;

import com.example.videoservice.modules.video.entities.YoutubeVideoStatisticsEntity;

public interface YoutubeVideoStatisticsService {
    void save(YoutubeVideoStatisticsEntity videoStatistics);
    void update(YoutubeVideoStatisticsEntity videoInfo);
    YoutubeVideoStatisticsEntity get(long id);
    List<YoutubeVideoStatisticsEntity> getAll();
}
