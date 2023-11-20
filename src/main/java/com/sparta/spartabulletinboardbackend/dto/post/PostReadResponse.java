package com.sparta.spartabulletinboardbackend.dto.post;

import com.sparta.spartabulletinboardbackend.domain.Comment;
import com.sparta.spartabulletinboardbackend.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostReadResponse {
    private final String username;
    private final String title;
    private final String content;
    private final String createdAt;
    private final boolean success;
    private final List<CommentReadData> comments = new ArrayList<>();

    @Builder
    public PostReadResponse(Post post, List<Comment> comments) {
        this.username = post.getUser().getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt().toString();
        this.success = post.isSuccess();

        for (Comment comment : comments) {
            this.comments.add(new CommentReadData(comment));
        }
    }

    @Getter
    static class CommentReadData {
        private final String commentUsername;
        private final String comment;
        private final String createdAt;

        public CommentReadData(Comment comment) {
            this.commentUsername = comment.getUser().getUsername();
            this.comment = comment.getComment();
            this.createdAt = comment.getCreatedAt().toString();
        }
    }
}
