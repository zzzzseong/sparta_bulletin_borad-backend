package com.sparta.spartabulletinboardbackend.user.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
class UserTest {

    @Test
    @DisplayName("유저 카카오 아이디 업데이트")
    public void updateKakaoId() {
        //given
        Long kakaoId = 100L;
        User user = new User("username", "email", "password", UserRole.USER, null);

        //when
        user.kakaoIdUpdate(kakaoId);

        //then
        assertEquals(user.getKakaoId(), kakaoId);
    }
}