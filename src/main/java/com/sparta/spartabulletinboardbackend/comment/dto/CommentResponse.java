package com.sparta.spartabulletinboardbackend.comment.dto;

import com.sparta.spartabulletinboardbackend.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentResponse {
    private final String username;
    private final String content;
    private final String createAt;

    @Builder
    public CommentResponse(Comment comment) {
        this.username = comment.getUser().getUsername();
        this.content = comment.getContent();
        this.createAt = comment.getCreatedAt().toString();
    }
}
