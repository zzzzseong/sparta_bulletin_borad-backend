package com.sparta.spartabulletinboardbackend.dto.user;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String username;
    private String password;
}