package com.sparta.spartabulletinboardbackend.repository;

import com.sparta.spartabulletinboardbackend.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p join fetch p.user order by p.user.id, p.createdAt desc")
    List<Post> findAllPostWithUsername();


    @Query("select p from Post p join fetch p.user where p.id = :post_id")
    Optional<Post> findPostWithUserById(@Param("post_id") Long postId);
}