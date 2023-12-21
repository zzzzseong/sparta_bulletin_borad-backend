package com.sparta.spartabulletinboardbackend.post.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PostListResponse {
    private final String username;
    private final List<PostResponse> posts;

    @Builder
    public PostListResponse(String username, List<PostResponse> posts) {
        this.username = username;
        this.posts = posts;
    }
}