package com.sparta.spartabulletinboardbackend.domain.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    UserRole userRole;

    private String username;
    private String password;

    public User(UserRole userRole, String username, String password) {
        this.userRole = userRole;
        this.username = username;
        this.password = password;
    }
}