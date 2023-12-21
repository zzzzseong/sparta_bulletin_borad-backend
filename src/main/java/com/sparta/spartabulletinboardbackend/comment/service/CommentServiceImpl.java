package com.sparta.spartabulletinboardbackend.comment.service;

import com.sparta.spartabulletinboardbackend.comment.entity.Comment;
import com.sparta.spartabulletinboardbackend.post.entity.Post;
import com.sparta.spartabulletinboardbackend.user.entity.User;
import com.sparta.spartabulletinboardbackend.comment.dto.CommentRequest;
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
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public Comment saveComment(User user, CommentRequest request, Long postId) {
        Post post = postRepository.findById(postId)
                        .orElseThrow(() -> new CustomException(CustomErrorCode.POST_NOT_EXIST_EXCEPTION, 404));

        Comment comment = Comment.builder()
                .user(user)
                .post(post)
                .comment(request.getComment())
                .build();

        commentRepository.save(comment);
        return comment;
    }

    public List<Comment> readAllComment(Long postId) {
        return commentRepository.findAll();
    }

    @Transactional
    public Comment updateComment(User user, CommentRequest request, Long commentId) {
        Comment findComment = getUserComment(user, commentId);
        return findComment.update(request.getComment());
    }

    @Transactional
    public Comment deleteComment(User user, Long commentId) {
        Comment comment = getUserComment(user, commentId);
        commentRepository.delete(comment);
        return comment;
    }

    private Comment getUserComment(User user, Long commentId) {
        Comment comment = commentRepository.findByIdWithUser(commentId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.COMMENT_NOT_EXIST_EXCEPTION, 404));

        if(!Objects.equals(user.getId(), comment.getUser().getId()))
            throw new CustomException(CustomErrorCode.NOT_ALLOWED_TO_UPDATE_COMMENT_EXCEPTION, 403);

        return comment;
    }
}
