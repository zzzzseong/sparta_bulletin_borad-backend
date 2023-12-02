package com.sparta.spartabulletinboardbackend.comment.service;

import com.sparta.spartabulletinboardbackend.comment.entity.Comment;
import com.sparta.spartabulletinboardbackend.post.entity.Post;
import com.sparta.spartabulletinboardbackend.user.entity.User;
import com.sparta.spartabulletinboardbackend.comment.dto.CommentCreateRequest;
import com.sparta.spartabulletinboardbackend.comment.dto.CommentUpdateRequest;
import com.sparta.spartabulletinboardbackend.common.exception.CustomErrorCode;
import com.sparta.spartabulletinboardbackend.common.exception.CustomException;
import com.sparta.spartabulletinboardbackend.comment.repository.CommentRepository;
import com.sparta.spartabulletinboardbackend.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        Comment comment = Comment.builder()
                .user(user)
                .post(findPost)
                .comment(request.getComment())
                .build();
        findPost.addComment(comment);
        return commentRepository.save(comment);
    }

    public List<Comment> readAllCommentWithUserByPostId(Long postId) {
        return commentRepository.findCommentWithUserAndPostByPostId(postId);
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
