package com.sparta.spartabulletinboardbackend.controller;

import com.sparta.spartabulletinboardbackend.dto.user.UserLoginRequest;
import com.sparta.spartabulletinboardbackend.dto.user.UserRegisterRequest;
import com.sparta.spartabulletinboardbackend.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
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

    @PostMapping("/auth/login")
    public void login(@RequestBody UserLoginRequest request, HttpServletResponse response) {
        userService.login(request, response);

        //login 성공시
    }
}