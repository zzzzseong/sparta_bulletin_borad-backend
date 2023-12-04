package com.sparta.spartabulletinboardbackend.test;

import com.sparta.spartabulletinboardbackend.user.entity.User;

public interface CommonTest {
    String ANOTHER_PREFIX = "another-";
    Long TEST_USER_ID = 1L;
    Long TEST_ANOTHER_USER_ID = 2L;
    String TEST_USER_NAME = "username";
    String TEST_USER_EMAIL = "email@email.com";
    String TEST_USER_PASSWORD = "password1A~";
    User TEST_USER = User.builder()
            .username(TEST_USER_NAME)
            .email(TEST_USER_EMAIL)
            .password(TEST_USER_PASSWORD)
            .build();
    User TEST_ANOTHER_USER = User.builder()
            .username(ANOTHER_PREFIX + TEST_USER_NAME)
            .email(ANOTHER_PREFIX + TEST_USER_EMAIL)
            .password(ANOTHER_PREFIX + TEST_USER_PASSWORD)
            .build();
}
