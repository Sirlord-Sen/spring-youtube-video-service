package com.example.videoservice.modules.video.services;

import java.util.List;

import com.example.videoservice.modules.video.entities.YoutubeVideoEntity;
import com.example.videoservice.modules.video.repositories.YoutubeVideoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YoutubeVideoServiceImpl implements YoutubeVideoService{
    
    @Autowired
    YoutubeVideoRepository youtubeVideoRepository;

    @Override
    public void save(YoutubeVideoEntity videoInfo) {
        youtubeVideoRepository.save(videoInfo);
    }

    @Override
    public void update(YoutubeVideoEntity videoInfo) {
        youtubeVideoRepository.save(videoInfo);
    }

    @Override
    public YoutubeVideoEntity getByVideoId(String videoId) {
        return youtubeVideoRepository.findByVideoId(videoId);
    }

    @Override
    public YoutubeVideoEntity get(long id) {
        return youtubeVideoRepository.findById(id).get();
    }

    @Override
    public List<YoutubeVideoEntity> getAll() {
        return youtubeVideoRepository.findAll();
    }
}
