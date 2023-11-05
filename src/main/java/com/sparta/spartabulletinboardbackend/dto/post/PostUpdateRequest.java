package com.sparta.spartabulletinboardbackend.dto.post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostUpdateRequest {
    private String title;
    private String author;
    private String description;
}
