package com.sparta.spartabulletinboardbackend.dto.post;

import com.sparta.spartabulletinboardbackend.domain.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostReadResponse {
    private final String username;
    private final String title;
    private final String content;
    private final String createdAt;
    private final boolean success;

    @Builder
    public PostReadResponse(Post post) {
        this.username = post.getUser().getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt().toString();
        this.success = post.isSuccess();
    }
}
