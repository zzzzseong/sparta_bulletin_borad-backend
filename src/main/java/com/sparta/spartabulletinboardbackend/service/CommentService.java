package com.sparta.spartabulletinboardbackend.service;

import com.sparta.spartabulletinboardbackend.domain.Comment;
import com.sparta.spartabulletinboardbackend.domain.Post;
import com.sparta.spartabulletinboardbackend.domain.user.User;
import com.sparta.spartabulletinboardbackend.dto.comment.CommentCreateRequest;
import com.sparta.spartabulletinboardbackend.dto.comment.CommentUpdateRequest;
import com.sparta.spartabulletinboardbackend.exception.CustomErrorCode;
import com.sparta.spartabulletinboardbackend.exception.CustomException;
import com.sparta.spartabulletinboardbackend.repository.CommentRepository;
import com.sparta.spartabulletinboardbackend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public Comment saveComment(User user, CommentCreateRequest request, Long postId) {
        Post findPost = postRepository.findById(postId)
                        .orElseThrow(() -> new CustomException(CustomErrorCode.POST_NOT_EXIST_EXCEPTION, 404));

        return commentRepository.save(new Comment(user, findPost, request.getComment()));
    }

    @Transactional
    public Comment updateComment(User user, CommentUpdateRequest request, Long commentId) {
        Comment findComment = commentRepository.findCommentWithUserById(commentId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.COMMENT_NOT_EXIST_EXCEPTION, 404));

        if(!Objects.equals(user.getId(), findComment.getUser().getId()))
            throw new CustomException(CustomErrorCode.NOT_ALLOWED_TO_UPDATE_COMMENT_EXCEPTION, 403);

        return findComment.update(request.getComment());
    }

    @Transactional
    public void deleteComment(User user, Long commentId) {
        Comment findComment = commentRepository.findCommentWithUserById(commentId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.COMMENT_NOT_EXIST_EXCEPTION, 404));

        if(!Objects.equals(user.getId(), findComment.getUser().getId()))
            throw new CustomException(CustomErrorCode.NOT_ALLOWED_TO_UPDATE_COMMENT_EXCEPTION, 403);

        commentRepository.delete(findComment);
    }
}
