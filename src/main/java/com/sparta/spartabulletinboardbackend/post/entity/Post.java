package com.sparta.spartabulletinboardbackend.post.entity;

import com.sparta.spartabulletinboardbackend.comment.entity.Comment;
import com.sparta.spartabulletinboardbackend.user.entity.User;
import com.sparta.spartabulletinboardbackend.post.dto.PostUpdateRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(columnDefinition = "TINYINT(1)", nullable = false)
    private boolean success;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private final List<Comment> comments = new ArrayList<>();

    @Builder
    public Post(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.success = false;
    }

    public Post update(PostUpdateRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
        return this;
    }

    public boolean updateSuccess() {
        this.success = !this.success;
        return this.success;
    }
}