package com.sparta.spartabulletinboardbackend.user.service;

import com.sparta.spartabulletinboardbackend.user.entity.User;
import com.sparta.spartabulletinboardbackend.user.entity.UserRole;
import com.sparta.spartabulletinboardbackend.user.dto.UserRegisterRequest;
import com.sparta.spartabulletinboardbackend.common.exception.CustomErrorCode;
import com.sparta.spartabulletinboardbackend.common.exception.CustomException;
import com.sparta.spartabulletinboardbackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User register(UserRegisterRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        //email 중복 검사
        if (userRepository.findByEmail(email).isPresent())
            throw new CustomException(CustomErrorCode.USER_EXISTS_EXCEPTION, 403);

        User user = User.builder()
                .username(request.getUsername())
                .email(email)
                .password(passwordEncoder.encode(password))
                .userRole(UserRole.USER)
                .build();

        userRepository.save(user);
        return user;
    }
}