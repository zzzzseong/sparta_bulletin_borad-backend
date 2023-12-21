package com.sparta.spartabulletinboardbackend.user.repository;

import com.sparta.spartabulletinboardbackend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByKakaoId(Long kakaoId);
}