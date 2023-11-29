package com.sparta.spartabulletinboardbackend.post.dto;

import com.sparta.spartabulletinboardbackend.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostCreateResponse {
    private final String username;
    private final String title;
    private final String content;
    private final String createdAt;
    private final boolean success;

    @Builder
    public PostCreateResponse(Post post) {
        this.username = post.getUser().getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt().toString();
        this.success = post.isSuccess();
    }
}