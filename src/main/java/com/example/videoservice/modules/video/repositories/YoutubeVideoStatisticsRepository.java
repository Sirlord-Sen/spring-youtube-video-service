package com.example.videoservice.modules.video.repositories;

import com.example.videoservice.modules.video.entities.YoutubeVideoStatisticsEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface YoutubeVideoStatisticsRepository extends JpaRepository<YoutubeVideoStatisticsEntity, Long>{
    
}
