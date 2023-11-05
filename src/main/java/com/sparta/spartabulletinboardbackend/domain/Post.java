package com.sparta.spartabulletinboardbackend.domain;

import com.sparta.spartabulletinboardbackend.dto.post.PostUpdateRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;
    private String author;
    private String password;
    private String description;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Post(String title, String author, String password, String description) {
        this.title = title;
        this.author = author;
        this.password = password;
        this.description = description;
    }

    public void update(PostUpdateRequest request) {
        this.title = request.getTitle();
        this.author = request.getAuthor();
        this.description = request.getDescription();
    }

    public boolean comparePassword(String password) {
        return Objects.equals(this.password, password);
    }
}