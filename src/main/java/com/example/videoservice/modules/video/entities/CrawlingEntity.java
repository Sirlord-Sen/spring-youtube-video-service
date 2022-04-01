package com.example.videoservice.modules.video.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity(name = "crawling_info")
@Data
@EqualsAndHashCode(callSuper=true)
public class CrawlingEntity extends BaseEntity{
    @Column(name = "search_key")
    private String searchKey;
    @Column(name = "current_page_token")
    private String currentPageToken;
    @Column(name = "next_page_token")
    private String nextPageToken;
    @Column(name = "total_count")
    private long totalCount;
}