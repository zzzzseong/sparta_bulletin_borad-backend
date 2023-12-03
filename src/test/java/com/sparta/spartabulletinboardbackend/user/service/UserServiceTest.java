package com.sparta.spartabulletinboardbackend.user.service;

import com.sparta.spartabulletinboardbackend.user.dto.UserRegisterRequest;
import com.sparta.spartabulletinboardbackend.user.entity.User;
import com.sparta.spartabulletinboardbackend.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock UserRepository userRepository;
    @Mock PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("사용자 회원가입(성공) - 유효성/중복 검사 통과, 정상 객체 생성")
    public void registerTestSuccess() {
        /* userService.register()의 역할
        - 조건에 부합하는 User 객체를 생성하는 역할을 한다.
        - userRepository.save()가 정상 동작하는지는 해당 테스트 레벨에서는 고려하지 않는다. */

        //given
        UserRegisterRequest request = new UserRegisterRequest();
        request.setUsername("username");
        request.setEmail("email@email.com");
        request.setPassword("password1A~");

        UserService userService = new UserService(userRepository, passwordEncoder);

        //when
        User user = userService.register(request);

        //then
        assertEquals(user.getUsername(), request.getUsername());
        assertEquals(user.getEmail(), request.getEmail());
        assertEquals(user.getPassword(), passwordEncoder.encode(request.getPassword()));
    }

//    @Test
//    @DisplayName("사용자 회원가입(실패) - 유효성 검사 부적합(이메일)")
//    public void registerTestEmailInvalid() {
//
//    }
//
//    @Test
//    @DisplayName("사용자 회원가입(실패) - 유효성 검사 부적합(비밀번호)")
//    public void registerTestPasswordInvalid() {
//
//    }
//
//    @Test
//    @DisplayName("사용자 회원가입(실패) - 이메일 중복 발생")
//    public void registerTestEmailDuplicate() {
//
//    }

}