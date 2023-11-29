package com.sparta.spartabulletinboardbackend.post.dto;

import lombok.Data;

@Data
public class PostCreateRequest {
    private String title;
    private String content;
}