package com.sparta.spartabulletinboardbackend.todo.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class TodoListResponse {
    private final String username;
    private final List<TodoResponse> posts;

    @Builder
    public TodoListResponse(String username, List<TodoResponse> posts) {
        this.username = username;
        this.posts = posts;
    }
}