package com.sparta.spartabulletinboardbackend.todo.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoRequest {

    @Pattern(regexp = ".{2,}")
    private String title;

    private String content;
}