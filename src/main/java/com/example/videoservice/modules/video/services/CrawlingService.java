package com.example.videoservice.modules.video.services;

import java.util.List;

import com.example.videoservice.modules.video.entities.CrawlingEntity;

public interface CrawlingService {
    void save(CrawlingEntity crawling);
    void update(CrawlingEntity crawling);
    CrawlingEntity get(Long id);
    CrawlingEntity getBySearchKey(String searchKey);
    List<CrawlingEntity> getAll();
}
