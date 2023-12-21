package com.sparta.spartabulletinboardbackend.comment.controller;

import com.sparta.spartabulletinboardbackend.comment.dto.CommentCreateRequest;
import com.sparta.spartabulletinboardbackend.comment.dto.CommentResponse;
import com.sparta.spartabulletinboardbackend.comment.dto.CommentUpdateRequest;
import com.sparta.spartabulletinboardbackend.comment.service.CommentService;
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

    private final CommentService commentService;

    @PostMapping("/{postId}") //댓글 작성(Test 완료)
    public ResponseEntity<CommentResponse> createComment(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody CommentCreateRequest request,
            @PathVariable(name = "postId") Long postId)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                CommentResponse.builder()
                        .comment(commentService.saveComment(userDetails.getUser(), postId, request))
                        .build()
        );
    }

    @PutMapping("/{commentId}") //댓글 수정(Test 완료)
    public ResponseEntity<CommentResponse> updateComment(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody CommentUpdateRequest request,
        @PathVariable(name = "commentId") Long commentId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(
                CommentResponse.builder()
                        .comment(commentService.updateComment(request, userDetails.getUser(), commentId))
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
