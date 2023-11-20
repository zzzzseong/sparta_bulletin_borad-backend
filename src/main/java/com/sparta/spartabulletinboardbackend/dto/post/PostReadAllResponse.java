package com.sparta.spartabulletinboardbackend.dto.post;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostReadAllResponse {
    private String username;
    private List<PostReadAllData> posts = new ArrayList<>();

    @Builder
    public PostReadAllResponse(String username, List<PostReadAllData> posts) {
        this.username = username;
        this.posts = posts;
    }
}
