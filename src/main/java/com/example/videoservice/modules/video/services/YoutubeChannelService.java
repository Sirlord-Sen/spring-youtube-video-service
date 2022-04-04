package com.example.videoservice.modules.video.services;

import java.util.List;

import com.example.videoservice.modules.video.entities.YoutubeChannelEntity;
// import com.google.api.client.repackaged.com.google.common.base.Optional;

public interface YoutubeChannelService {
    void save(YoutubeChannelEntity channelInfo);
    void update(YoutubeChannelEntity channelInfo);
    YoutubeChannelEntity get(long id);
    YoutubeChannelEntity getByChannelId(String channelId);
    List<YoutubeChannelEntity> getAll();
}
