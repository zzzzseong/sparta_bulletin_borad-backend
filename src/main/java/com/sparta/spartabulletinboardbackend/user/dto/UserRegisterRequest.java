package com.sparta.spartabulletinboardbackend.user.dto;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String username;
    private String password;
}