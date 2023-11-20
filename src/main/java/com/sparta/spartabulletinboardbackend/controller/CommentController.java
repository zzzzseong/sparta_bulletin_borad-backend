package com.sparta.spartabulletinboardbackend.controller;

import com.sparta.spartabulletinboardbackend.dto.comment.CommentCreateRequest;
import com.sparta.spartabulletinboardbackend.dto.comment.CommentReadResponse;
import com.sparta.spartabulletinboardbackend.dto.comment.CommentUpdateRequest;
import com.sparta.spartabulletinboardbackend.security.UserDetailsImpl;
import com.sparta.spartabulletinboardbackend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}") //댓글 작성
    public CommentReadResponse createComment(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                             @RequestBody CommentCreateRequest request,
                                             @PathVariable(name = "postId") Long postId) {
        return CommentReadResponse.builder()
                .comment(commentService.saveComment(userDetails.getUser(), request, postId))
                .build();
    }

    @PutMapping("/{commentId}") //댓글 수정
    public CommentReadResponse updateComment(@AuthenticationPrincipal UserDetailsImpl userDetails,
                              @RequestBody CommentUpdateRequest request,
                              @PathVariable(name = "commentId") Long commentId) {
        return CommentReadResponse.builder()
                .comment(commentService.updateComment(userDetails.getUser(), request, commentId))
                .build();
    }

    @DeleteMapping("/{commentId}") //댓글 삭제
    public void deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails,
                              @PathVariable(name = "commentId") Long commentId) {
        commentService.deleteComment(userDetails.getUser(), commentId);
    }
}
