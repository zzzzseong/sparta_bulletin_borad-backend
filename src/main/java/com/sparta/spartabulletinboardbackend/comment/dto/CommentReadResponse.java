package com.sparta.spartabulletinboardbackend.comment.dto;

import com.sparta.spartabulletinboardbackend.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentReadResponse {
    private final String username;
    private final String comment;
    private final String createAt;

    @Builder
    public CommentReadResponse(Comment comment) {
        this.username = comment.getUser().getUsername();
        this.comment = comment.getComment();
        this.createAt = comment.getCreatedAt().toString();
    }
}
