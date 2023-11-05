package com.sparta.spartabulletinboardbackend.repository;

import com.sparta.spartabulletinboardbackend.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
