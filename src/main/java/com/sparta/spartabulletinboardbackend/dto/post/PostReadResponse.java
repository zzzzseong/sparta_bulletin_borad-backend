package com.sparta.spartabulletinboardbackend.dto.post;

import com.sparta.spartabulletinboardbackend.domain.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostReadResponse {
    private String title;
    private String author;
    private String description;
    private String createdAt;

    public PostReadResponse(Post post) {
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.description = post.getDescription();
        this.createdAt = String.valueOf(post.getCreatedAt());
    }
}
