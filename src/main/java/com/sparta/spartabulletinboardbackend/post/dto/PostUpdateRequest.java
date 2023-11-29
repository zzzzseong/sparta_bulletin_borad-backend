package com.sparta.spartabulletinboardbackend.post.dto;

import lombok.Data;

@Data
public class PostUpdateRequest {
    private String title;
    private String content;
}
