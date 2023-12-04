package com.sparta.spartabulletinboardbackend.user.dto;

import com.sparta.spartabulletinboardbackend.test.CommonTest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
class UserRegisterRequestTest implements CommonTest {

    @Nested
    @DisplayName("유저 회원가입 요청 DTO 생성 성공")
    class userRegisterRequestTest {

        @Test
        @DisplayName("유저 회원가입 요청 DTO 생성 성공")
        public void createUserRegisterRequest_success() {
            //given
            UserRegisterRequest request = new UserRegisterRequest();
            request.setEmail(TEST_USER_EMAIL);
            request.setPassword(TEST_USER_PASSWORD);

            //when
            Set<ConstraintViolation<UserRegisterRequest>> violations = validate(request);

            //then
            assertThat(violations).isEmpty();
        }

        @Test
        @DisplayName("유저 회원가입 요청 DTO 생성 실패 - 잘못된 이메일")
        public void createUserRegisterRequest_fail_wrongEmail() {
            //given
            UserRegisterRequest request = new UserRegisterRequest();
            request.setEmail("invalid email");
            request.setPassword(TEST_USER_PASSWORD);

            //when
            Set<ConstraintViolation<UserRegisterRequest>> violations = validate(request);

            //then
            assertThat(violations).hasSize(1);
            assertThat(violations)
                    .extracting("message")
                    .contains("\"\\w+@\\w+\\.\\w+(\\.\\w+)?\"와 일치해야 합니다");
        }

        @Test
        @DisplayName("유저 회원가입 요청 DTO 생성 실패 - 잘못된 비밀번호")
        public void createUserRegisterRequest_fail_wrongPassword() {
            //given
            UserRegisterRequest request = new UserRegisterRequest();
            request.setEmail(TEST_USER_EMAIL);
            request.setPassword("invalid password");

            //when
            Set<ConstraintViolation<UserRegisterRequest>> violations = validate(request);

            //then
            assertThat(violations).hasSize(1);
            assertThat(violations)
                    .extracting("message")
                    .contains("\"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[~@#$%^&+=!])(?=\\S+$).{8,15}$\"와 일치해야 합니다");
        }

        private Set<ConstraintViolation<UserRegisterRequest>> validate(UserRegisterRequest request) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            return validator.validate(request);
        }
    }

}