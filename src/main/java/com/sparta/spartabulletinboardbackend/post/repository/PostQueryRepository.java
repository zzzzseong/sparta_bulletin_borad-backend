package com.sparta.spartabulletinboardbackend.post.repository;

import com.sparta.spartabulletinboardbackend.post.entity.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostQueryRepository {
    List<Post> search(PostSearchCond cond, Pageable pageable);
}
