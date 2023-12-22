package com.sparta.spartabulletinboardbackend.todo.dto;

import com.sparta.spartabulletinboardbackend.todo.entity.Todo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TodoResponse {
    private final String username;
    private final String title;
    private final String content;
    private final String createdAt;
    private final boolean success;

    @Builder
    public TodoResponse(Todo todo) {
        this.username = todo.getUser().getUsername();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.createdAt = todo.getCreatedAt().toString();
        this.success = todo.isSuccess();
    }
}