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
    private long favoriteCount;

    @Column(name = "comment_count")
    private long commentCount;

    @Column(name = "video_id")
    private String videoId;


    public void setVideoId(String videoId){
        this.videoId = videoId;
    }

    public void setLikeCount(Long likeCount){
        this.likeCount = likeCount;
    }

    public void setDislikeCount(Long dislikeCount){
        this.dislikeCount = dislikeCount;
    }

    public void setViewCount(Long viewCount){
         this.viewCount = viewCount;
    }

    public void setFavoriteCount(Long favoriteCount){
         this.favoriteCount = favoriteCount;
    }

    public void setCommentCount(Long commentCount){
         this.commentCount = commentCount;
    }
}
