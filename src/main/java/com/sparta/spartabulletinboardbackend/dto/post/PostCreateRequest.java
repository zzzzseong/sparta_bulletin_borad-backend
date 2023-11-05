package com.sparta.spartabulletinboardbackend.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateRequest {
    private String title;
    private String author;
    private String password;
    private String description;
}
