package com.sparta.spartabulletinboardbackend.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.spartabulletinboardbackend.common.jwt.JwtUtil;
import com.sparta.spartabulletinboardbackend.user.dto.UserRegisterRequest;
import com.sparta.spartabulletinboardbackend.user.service.KakaoService;
import com.sparta.spartabulletinboardbackend.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final KakaoService kakaoService;

    @PostMapping("/auth/register")
    public void register(@RequestBody UserRegisterRequest request) {
        userService.register(request);
    }

    @GetMapping("/auth/kakao/callback")
    public void kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        System.out.println("check 1");
        String token = kakaoService.kakaoLogin(code);
        System.out.println("check 2");
        jwtUtil.addJwtToCookie(token, response);
        System.out.println("check 3");
    }

}