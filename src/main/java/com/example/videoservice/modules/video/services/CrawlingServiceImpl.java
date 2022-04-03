package com.example.videoservice.modules.video.services;

import java.util.List;

import com.example.videoservice.modules.video.entities.CrawlingEntity;
import com.example.videoservice.modules.video.repositories.CrawlingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrawlingServiceImpl implements CrawlingService{
    
    @Autowired
    private CrawlingRepository crawlingRepository;

    @Override
    public void save(CrawlingEntity crawling){
        crawlingRepository.save(crawling);
    }

    @Override
    public void update(CrawlingEntity crawling){
        crawlingRepository.save(crawling);
    }

    @Override
    public CrawlingEntity get(Long Id){
        return crawlingRepository.getOne(Id);
    }

    @Override
    public CrawlingEntity getBySearchKey(String searchKey) {
        return crawlingRepository.findBySearchKey(searchKey);
    }

    @Override
    public List<CrawlingEntity> getAll() {
        return crawlingRepository.findAll();
    }
}
