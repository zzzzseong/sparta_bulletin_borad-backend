package com.sparta.spartabulletinboardbackend.comment.service;

import com.sparta.spartabulletinboardbackend.comment.entity.Comment;
import com.sparta.spartabulletinboardbackend.comment.repository.CommentQueryRepository;
import com.sparta.spartabulletinboardbackend.common.consts.ServiceConst;
import com.sparta.spartabulletinboardbackend.todo.entity.Todo;
import com.sparta.spartabulletinboardbackend.user.entity.User;
import com.sparta.spartabulletinboardbackend.comment.dto.CommentRequest;
import com.sparta.spartabulletinboardbackend.common.exception.CustomErrorCode;
import com.sparta.spartabulletinboardbackend.common.exception.CustomException;
import com.sparta.spartabulletinboardbackend.comment.repository.CommentRepository;
import com.sparta.spartabulletinboardbackend.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "CommentService")
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentQueryRepository commentQueryRepository;
    private final TodoRepository todoRepository;

    @Override
    @Transactional
    public Comment saveComment(User user, CommentRequest request, Long postId) {
        Todo todo = todoRepository.findById(postId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.POST_NOT_EXIST_EXCEPTION, 404));

        Comment comment = Comment.builder()
                .user(user)
                .todo(todo)
                .content(request.getComment())
                .build();

        commentRepository.save(comment);
        return comment;
    }

    @Override
    public List<Comment> readComment(Long postId, int page) {

        //작성일 기준 내림차순 정렬
        Pageable pageable = PageRequest.of(page, ServiceConst.DEFAULT_BATCH_SIZE, Sort.Direction.DESC, "createdAt");

        return commentQueryRepository.readCommentAll(postId, pageable);
    }

    @Override
    @Transactional
    public Comment updateComment(User user, CommentRequest request, Long commentId) {
        Comment findComment = getUserComment(user, commentId);
        return findComment.update(request.getComment());
    }

    @Override
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

