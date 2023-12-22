package com.sparta.spartabulletinboardbackend.todo.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TodoRequest {

    @Pattern(regexp = ".{2,}")
    private String title;

    private String content;

    @Builder
    public TodoRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}