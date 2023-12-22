package com.sparta.spartabulletinboardbackend.todo.repository;

import lombok.Getter;

@Getter
public class TodoSearchCond {
    private final String keyword;

    public TodoSearchCond(String keyword) {
        this.keyword = keyword;
    }
}
