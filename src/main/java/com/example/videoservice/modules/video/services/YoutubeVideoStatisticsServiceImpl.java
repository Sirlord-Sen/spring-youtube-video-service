package com.example.videoservice.modules.video.services;

import java.util.List;

import com.example.videoservice.modules.video.entities.YoutubeVideoStatisticsEntity;
import com.example.videoservice.modules.video.repositories.YoutubeVideoStatisticsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YoutubeVideoStatisticsServiceImpl implements YoutubeVideoStatisticsService{

    @Autowired
    YoutubeVideoStatisticsRepository youtubeVideoStatisticsRepository;

    @Override
    public void save(YoutubeVideoStatisticsEntity videoStatistics) {
        youtubeVideoStatisticsRepository.save(videoStatistics);
    }

    @Override
    public void update(YoutubeVideoStatisticsEntity videoStatistics) {
        youtubeVideoStatisticsRepository.save(videoStatistics);
    }

    @Override
    public YoutubeVideoStatisticsEntity get(long id) {
        return youtubeVideoStatisticsRepository.findById(id).get();
    }

    @Override
    public List<YoutubeVideoStatisticsEntity> getAll() {
        return youtubeVideoStatisticsRepository.findAll();
    }
}
