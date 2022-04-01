package com.example.videoservice.modules.video.repositories;

import com.example.videoservice.modules.video.entities.YoutubeChannelEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YoutubeChannelRepository extends JpaRepository<YoutubeChannelEntity, Long>{
    
}
