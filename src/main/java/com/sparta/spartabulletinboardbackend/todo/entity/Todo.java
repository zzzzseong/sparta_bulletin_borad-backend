package com.sparta.spartabulletinboardbackend.todo.entity;

import com.sparta.spartabulletinboardbackend.common.BaseEntity;
import com.sparta.spartabulletinboardbackend.todo.dto.TodoRequest;
import com.sparta.spartabulletinboardbackend.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo extends BaseEntity {

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

    @Builder
    public Todo(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.success = false;
    }

    public Todo update(TodoRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
        return this;
    }

    public boolean updateSuccess() {
        this.success = !this.success;
        return this.success;
    }
}