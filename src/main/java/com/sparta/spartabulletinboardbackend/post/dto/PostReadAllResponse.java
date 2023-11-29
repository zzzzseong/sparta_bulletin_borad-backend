package com.sparta.spartabulletinboardbackend.post.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PostReadAllResponse {
    private String username;
    private List<PostReadAllData> posts;

    @Builder
    public PostReadAllResponse(String username, List<PostReadAllData> posts) {
        this.username = username;
        this.posts = posts;
    }
}