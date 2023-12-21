package com.sparta.spartabulletinboardbackend.comment.controller;

import com.sparta.spartabulletinboardbackend.comment.dto.CommentRequest;
import com.sparta.spartabulletinboardbackend.comment.dto.CommentResponse;
import com.sparta.spartabulletinboardbackend.comment.service.CommentServiceImpl;
import com.sparta.spartabulletinboardbackend.user.entity.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentServiceImpl commentService;

    @PostMapping("/{postId}") //댓글 작성(Test 완료)
    public ResponseEntity<CommentResponse> createComment(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody CommentRequest request,
            @PathVariable(name = "postId") Long postId)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                CommentResponse.builder()
                        .comment(commentService.saveComment(userDetails.getUser(), request, postId))
                        .build()
        );
    }

    @PutMapping("/{commentId}") //댓글 수정(Test 완료)
    public ResponseEntity<CommentResponse> updateComment(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody CommentRequest request,
        @PathVariable(name = "commentId") Long commentId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(
                CommentResponse.builder()
                        .comment(commentService.updateComment(userDetails.getUser(), request, commentId))
                        .build()
        );
    }

    @DeleteMapping("/{commentId}") //댓글 삭제(Test 완료)
    public ResponseEntity<CommentResponse> deleteComment(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable(name = "commentId") Long commentId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(
                CommentResponse.builder()
                        .comment(commentService.deleteComment(userDetails.getUser(), commentId))
                        .build()
        );
    }
}
