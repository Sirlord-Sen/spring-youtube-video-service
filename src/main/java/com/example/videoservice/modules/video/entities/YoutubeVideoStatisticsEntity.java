package com.example.videoservice.modules.video.entities;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "youtube_video_stat")
@Data
@EqualsAndHashCode(callSuper=true)
public class YoutubeVideoStatisticsEntity extends BaseEntity{

    @Column(name = "like_count")
    private long likeCount;

    @Column(name = "dislike_count")
    private long dislikeCount;

    @Column(name = "view_count")
    private long viewCount;

    @Column(name = "favorite_count")
    private long favorite_count;

    @Column(name = "comment_count")
    private long comment_count;

    @Column(name = "video_id")
    private String videoId;
}
