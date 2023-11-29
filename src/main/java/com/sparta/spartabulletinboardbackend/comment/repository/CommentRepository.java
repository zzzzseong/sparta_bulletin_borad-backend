package com.sparta.spartabulletinboardbackend.comment.repository;

import com.sparta.spartabulletinboardbackend.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c join fetch c.user where c.id = :comment_id")
    Optional<Comment> findCommentWithUserById(@Param("comment_id") Long commentId);

    @Query("select c from Comment c join fetch c.user where c.post.id = :post_id")
    List<Comment> findCommentWithUserAndPostByPostId(@Param("post_id") Long postId);
}
