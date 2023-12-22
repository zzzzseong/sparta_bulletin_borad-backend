package com.sparta.spartabulletinboardbackend.comment.repository;

import com.sparta.spartabulletinboardbackend.comment.entity.Comment;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentQueryRepository {

    List<Comment> readCommentAll(Long postId, Pageable pageable);
}
