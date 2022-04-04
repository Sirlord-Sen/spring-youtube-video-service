package com.example.videoservice.modules.video.services;

import java.util.List;

import com.example.videoservice.modules.video.entities.YoutubeChannelEntity;
import com.example.videoservice.modules.video.repositories.YoutubeChannelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YoutubeChannelServiceImpl implements YoutubeChannelService {

    @Autowired
    YoutubeChannelRepository youtubeChannelRepository;


    @Override
    public void save(YoutubeChannelEntity channelInfo){
        youtubeChannelRepository.save(channelInfo);
    }

    @Override
    public void update(YoutubeChannelEntity channelInfo) {
        youtubeChannelRepository.save(channelInfo);
    }

    @Override
    public YoutubeChannelEntity get(long id) {
        return youtubeChannelRepository.findById(id).get();
    }

    @Override
    public YoutubeChannelEntity getByChannelId(String channelId) {
        return youtubeChannelRepository.findByChannelId(channelId);
    }

    @Override
    public List<YoutubeChannelEntity> getAll() {
        return youtubeChannelRepository.findAll();
    }
}
