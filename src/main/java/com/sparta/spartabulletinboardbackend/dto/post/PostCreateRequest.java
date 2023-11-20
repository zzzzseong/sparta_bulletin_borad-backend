package com.sparta.spartabulletinboardbackend.dto.post;

import lombok.Data;

@Data
public class PostCreateRequest {
    private String title;
    private String content;
}