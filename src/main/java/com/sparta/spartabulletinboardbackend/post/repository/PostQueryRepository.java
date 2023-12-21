package com.sparta.spartabulletinboardbackend.post.repository;

import com.sparta.spartabulletinboardbackend.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostQueryRepository {
    Page<Post> search(PostSearchCond cond, Pageable pageable);
}
