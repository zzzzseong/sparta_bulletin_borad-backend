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
    public void register(UserRegisterRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        //username & password 유효성 검사
        if (!username.matches("^[a-z0-9]{4,10}$"))
            throw new CustomException(CustomErrorCode.USERNAME_INVALID_EXCEPTION, 403);
        if (!password.matches("^[a-zA-Z0-9]{8,15}$"))
            throw new CustomException(CustomErrorCode.PASSWORD_INVALID_EXCEPTION, 403);

        //username 중복 검사
        if (userRepository.findByUsername(username).isPresent())
            throw new CustomException(CustomErrorCode.USER_EXISTS_EXCEPTION, 403);

        User user = new User(UserRole.USER, username, passwordEncoder.encode(password));
        userRepository.save(user);
    }
}