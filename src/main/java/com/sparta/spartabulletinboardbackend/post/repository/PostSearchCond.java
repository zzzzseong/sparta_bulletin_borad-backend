package com.sparta.spartabulletinboardbackend.post.repository;

import lombok.Getter;

@Getter
public class PostSearchCond {
    private final String keyword;

    public PostSearchCond(String keyword) {
        this.keyword = keyword;
    }
}
