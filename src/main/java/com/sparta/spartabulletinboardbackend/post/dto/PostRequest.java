package com.sparta.spartabulletinboardbackend.post.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {

    @Pattern(regexp = ".{2,}")
    private String title;

    private String content;
}