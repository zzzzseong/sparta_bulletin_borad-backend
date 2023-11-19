package com.sparta.spartabulletinboardbackend.controller;

import com.sparta.spartabulletinboardbackend.dto.user.UserRegisterRequest;
import com.sparta.spartabulletinboardbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/auth/register")
    public void register(@RequestBody UserRegisterRequest request) {
        userService.register(request);
    }
}