package com.sparta.spartabulletinboardbackend.user.service;

import com.sparta.spartabulletinboardbackend.common.exception.CustomErrorCode;
import com.sparta.spartabulletinboardbackend.common.exception.CustomException;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


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

    @Test
    @DisplayName("사용자 회원가입(실패) - 유효성 검사 부적합(이메일)")
    public void registerTestEmailInvalid() {
        //given
        UserRegisterRequest request1 = new UserRegisterRequest();
        request1.setUsername("username");
        request1.setEmail("emailemail.com");
        request1.setPassword("password1A~");

        UserRegisterRequest request2 = new UserRegisterRequest();
        request2.setUsername("username");
        request2.setEmail("email@emailcom");
        request2.setPassword("password1A~");

        UserRegisterRequest request3 = new UserRegisterRequest();
        request3.setUsername("username");
        request3.setEmail("email@@email.com");
        request3.setPassword("password1A~");

        UserRegisterRequest request4 = new UserRegisterRequest();
        request4.setUsername("username");
        request4.setEmail("email@email..com");
        request4.setPassword("password1A~");

        UserService userService = new UserService(userRepository, passwordEncoder);

        //when
        CustomException exception1 = assertThrows(CustomException.class, () -> userService.register(request1));
        CustomException exception2 = assertThrows(CustomException.class, () -> userService.register(request2));
        CustomException exception3 = assertThrows(CustomException.class, () -> userService.register(request3));
        CustomException exception4 = assertThrows(CustomException.class, () -> userService.register(request4));

        //then
        assertEquals(CustomErrorCode.EMAIL_INVALID_EXCEPTION, exception1.getErrorCode());
        assertEquals(CustomErrorCode.EMAIL_INVALID_EXCEPTION, exception2.getErrorCode());
        assertEquals(CustomErrorCode.EMAIL_INVALID_EXCEPTION, exception3.getErrorCode());
        assertEquals(CustomErrorCode.EMAIL_INVALID_EXCEPTION, exception4.getErrorCode());
    }
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