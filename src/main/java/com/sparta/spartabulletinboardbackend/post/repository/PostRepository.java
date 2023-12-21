package com.sparta.spartabulletinboardbackend.post.repository;

import com.sparta.spartabulletinboardbackend.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p join fetch p.user order by p.user.id, p.createdAt desc")
    List<Post> findAllPostWithUser();
}