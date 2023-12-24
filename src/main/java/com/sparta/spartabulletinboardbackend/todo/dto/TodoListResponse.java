package com.sparta.spartabulletinboardbackend.todo.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class TodoListResponse {
    private final String username;
    private final List<TodoResponse> todoList;

    @Builder
    public TodoListResponse(String username, List<TodoResponse> todoList) {
        this.username = username;
        this.todoList = todoList;
    }
}