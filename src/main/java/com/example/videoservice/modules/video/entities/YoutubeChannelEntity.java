package com.example.videoservice.modules.video.entities;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "youtube_channel")
@Data
@EqualsAndHashCode(callSuper=true)
public class YoutubeChannelEntity extends BaseEntity {
    
    @Column(name = "channel_id")
    private String channelId;

    @Column(name = "name")
    private String name;

    @Column(name = "subscription_count")
    private long subscriptionCount; 
}
