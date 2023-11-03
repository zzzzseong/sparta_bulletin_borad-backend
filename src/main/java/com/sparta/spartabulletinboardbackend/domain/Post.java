package com.sparta.spartabulletinboardbackend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;
    private String author;
    private String password;
    private String description;
    private Date createdDate;

    public Post(String title, String author, String password, String description, Date createdDate) {
        this.title = title;
        this.author = author;
        this.password = password;
        this.description = description;
        this.createdDate = createdDate;
    }
}