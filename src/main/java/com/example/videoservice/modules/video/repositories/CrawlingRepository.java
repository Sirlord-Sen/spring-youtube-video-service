package com.example.videoservice.modules.video.repositories;

import com.example.videoservice.modules.video.entities.CrawlingEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CrawlingRepository extends JpaRepository<CrawlingEntity, Long> {
    CrawlingEntity findBySearchKey(String searchKey);
}
