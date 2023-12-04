package com.sparta.spartabulletinboardbackend.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.spartabulletinboardbackend.common.jwt.JwtUtil;
import com.sparta.spartabulletinboardbackend.user.dto.UserRegisterRequest;
import com.sparta.spartabulletinboardbackend.user.service.KakaoService;
import com.sparta.spartabulletinboardbackend.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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
    public void register(@Valid @RequestBody UserRegisterRequest request) {
        //@Valid를 이용해 request의 @Pattern을 검사한다.
        //검사 결과 @Pattern이 걸려있는 email과 password의 값이 유효하지 않으면 MethodArgumentNotValidException이 발생한다.
        //MethodArgumentNotValidException이 발생하면 CustomExceptionHandler를 이용해 클라이언트에 예외를 반환한다.
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