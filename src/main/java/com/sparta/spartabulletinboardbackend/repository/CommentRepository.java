package com.sparta.spartabulletinboardbackend.repository;

import com.sparta.spartabulletinboardbackend.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c join fetch c.user where c.id = :comment_id")
    Optional<Comment> findCommentWithUserById(Long commentId);
}
