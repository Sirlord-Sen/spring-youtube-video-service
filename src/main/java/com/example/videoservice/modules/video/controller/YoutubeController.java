package com.example.videoservice.modules.video.controller;

import java.util.List;

import com.example.videoservice.modules.video.entities.YoutubeChannelEntity;
import com.example.videoservice.modules.video.entities.YoutubeVideoEntity;
import com.example.videoservice.modules.video.entities.YoutubeVideoStatisticsEntity;
import com.example.videoservice.modules.video.services.YoutubeApiService;
import com.example.videoservice.modules.video.services.YoutubeChannelService;
import com.example.videoservice.modules.video.services.YoutubeVideoService;
import com.example.videoservice.modules.video.services.YoutubeVideoStatisticsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("youtube")
public class YoutubeController {
    
    @Autowired
    YoutubeApiService youtubeApiService;

    @Autowired
    YoutubeVideoService youtubeVideoService;

    @Autowired
    YoutubeChannelService youtubeChannelService;

    @Autowired
    YoutubeVideoStatisticsService youtubeVideoStatisticsService;

    @GetMapping(value = "crawl/{keyword}/{pageToCrawl}")
    public String crawlVideo(@PathVariable String keyword, @PathVariable long pageToCrawl) {
        return youtubeApiService.crawlYoutubeVideoInfo(keyword,pageToCrawl);
    }

    @GetMapping
    public List<YoutubeVideoEntity> getAll(){
        return youtubeVideoService.getAll();
    }

    @GetMapping(value = "{id}")
    public YoutubeVideoEntity getOne(@PathVariable long id){
        return youtubeVideoService.get(id);
    }

    @GetMapping(value = "channel")
    public List<YoutubeChannelEntity> getAllChannel(){
        return youtubeChannelService.getAll();
    }

    @GetMapping(value = "channel/{id}")
    public YoutubeChannelEntity getChannel(@PathVariable long id){
        return youtubeChannelService.get(id);
    }

    @GetMapping(value = "stat")
    public List<YoutubeVideoStatisticsEntity> getAllstat(){
        return youtubeVideoStatisticsService.getAll();
    }

    @GetMapping(value = "stat/{id}")
    public YoutubeVideoStatisticsEntity getStats(@PathVariable long id){
        return youtubeVideoStatisticsService.get(id);
    }
}
