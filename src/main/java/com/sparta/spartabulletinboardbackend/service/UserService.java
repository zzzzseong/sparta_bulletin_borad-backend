package com.sparta.spartabulletinboardbackend.service;

import com.sparta.spartabulletinboardbackend.domain.user.User;
import com.sparta.spartabulletinboardbackend.domain.user.UserRole;
import com.sparta.spartabulletinboardbackend.dto.user.UserRegisterRequest;
import com.sparta.spartabulletinboardbackend.exception.CustomErrorCode;
import com.sparta.spartabulletinboardbackend.exception.CustomException;
import com.sparta.spartabulletinboardbackend.repository.UserRepository;
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
        String password = passwordEncoder.encode(request.getPassword());

        //username & password 유효성 검사
        if (!username.matches("^[a-z0-9]{4,10}$"))
            throw new CustomException(CustomErrorCode.USERNAME_INVALID_EXCEPTION, 403);
        if (!password.matches("^[a-zA-Z0-9]{8,15}$"))
            throw new CustomException(CustomErrorCode.PASSWORD_INVALID_EXCEPTION, 403);

        //username 중복 검사
        if (userRepository.findByUsername(username).isPresent())
            throw new CustomException(CustomErrorCode.USER_ALREADY_EXISTS_EXCEPTION, 403);


        User user = new User(UserRole.USER, username, password);
        userRepository.save(user);
    }
}