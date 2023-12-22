package com.sparta.spartabulletinboardbackend.comment.entity;

import com.sparta.spartabulletinboardbackend.common.BaseEntity;
import com.sparta.spartabulletinboardbackend.todo.entity.Todo;
import com.sparta.spartabulletinboardbackend.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Todo todo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder
    public Comment(User user, Todo todo, String content) {
        this.user = user;
        this.todo = todo;
        this.content = content;
    }

    public Comment update(String content) {
        this.content = content;
        return this;
    }
}
