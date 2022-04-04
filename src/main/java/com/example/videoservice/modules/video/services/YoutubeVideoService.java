package com.example.videoservice.modules.video.services;

import java.util.List;

import com.example.videoservice.modules.video.entities.YoutubeVideoEntity;

public interface YoutubeVideoService {
    void save(YoutubeVideoEntity videoInfo);
    void update(YoutubeVideoEntity videoInfo);
    YoutubeVideoEntity getByVideoId(String videoId);
    YoutubeVideoEntity get(long id);
    List<YoutubeVideoEntity> getAll();
}
