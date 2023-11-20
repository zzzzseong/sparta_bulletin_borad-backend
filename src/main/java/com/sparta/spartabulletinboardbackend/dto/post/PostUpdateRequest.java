package com.sparta.spartabulletinboardbackend.dto.post;

import lombok.Data;

@Data
public class PostUpdateRequest {
    private String title;
    private String content;
}
