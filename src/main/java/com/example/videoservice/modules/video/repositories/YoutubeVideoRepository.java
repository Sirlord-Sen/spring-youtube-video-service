package com.example.videoservice.modules.video.repositories;

import com.example.videoservice.modules.video.entities.YoutubeVideoEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface YoutubeVideoRepository extends JpaRepository<YoutubeVideoEntity, Long>{
    YoutubeVideoEntity findByVideoId(String videoId);
}
