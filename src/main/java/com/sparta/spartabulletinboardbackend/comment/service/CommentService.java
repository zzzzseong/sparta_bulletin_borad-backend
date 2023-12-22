package com.sparta.spartabulletinboardbackend.comment.service;

import com.sparta.spartabulletinboardbackend.comment.dto.CommentRequest;
import com.sparta.spartabulletinboardbackend.comment.entity.Comment;
import com.sparta.spartabulletinboardbackend.user.entity.User;

import java.util.List;

public interface CommentService {

    /**
     * Comment 생성
     * @param user Comment 생성 요청자
     * @param request Comment 생성 요청 정보
     * @param postId Comment를 생성할 TODO ID
     * @return Comment 생성 결과
     * */
    Comment saveComment(User user, CommentRequest request, Long postId);

    /**
     * Comment 조회
     * @param postId Comment 조회 TODO ID
     * @param page Comment 조회 페이지
     * @return Comment 조회 결과
     * */
    List<Comment> readComment(Long postId, int page);


    /**
     * Comment 업데이트
     * @param user Comment 업데이트 요청자
     * @param request Comment 업데이트 요청 정보
     * @param commentId 업데이트할 Comment ID
     * @return Comment 업데이트 결과
     * */
    Comment updateComment(User user, CommentRequest request, Long commentId);

    /**
     * Comment 삭제
     * @param user Comment 삭제 요청자
     * @param commentId 삭제할 Comment ID
     * @return Comment 삭제 결과
     * */
    Comment deleteComment(User user, Long commentId);
}
