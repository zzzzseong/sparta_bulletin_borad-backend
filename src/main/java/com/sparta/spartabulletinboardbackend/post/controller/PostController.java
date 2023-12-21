package com.sparta.spartabulletinboardbackend.post.controller;

import com.sparta.spartabulletinboardbackend.comment.service.CommentService;
import com.sparta.spartabulletinboardbackend.post.dto.*;
import com.sparta.spartabulletinboardbackend.post.sevice.PostService;
import com.sparta.spartabulletinboardbackend.user.entity.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;
    private final CommentService commentService;

    @PostMapping("") //할일카드 작성(Test 완료)
    public ResponseEntity<PostResponse> createPost(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody PostCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                PostResponse.builder()
                    .post(postService.savePost(userDetails.getUser(), request))
                    .build()
        );
    }

    @GetMapping("") //할일카드 목록 조회(Test 완료)
    public ResponseEntity<List<PostReadAllResponse>> readPostAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                postService.readAllPost()
        );
    }

    @GetMapping("/{postId}") //할일카드 단일 조회(Test 완료)
    public ResponseEntity<PostReadResponse> readPost(@PathVariable(name = "postId") Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                PostReadResponse.builder()
                    .post(postService.readPost(postId))
                    .comments(commentService.readAllCommentWithUserByPostId(postId))
                    .build()
        );
    }

    @PutMapping("/{postId}") //할일카드 수정(Test 완료)
    public ResponseEntity<PostResponse> updatePost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                       @RequestBody PostUpdateRequest request,
                                       @PathVariable(name = "postId") Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                PostResponse.builder()
                    .post(postService.updatePost(userDetails.getUser(), postId, request))
                    .build()
        );
    }

    @GetMapping("/success/{postId}") //할일카드 완료(Test 완료)
    public ResponseEntity<Boolean> updateSuccess(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                 @PathVariable(name = "postId") Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                postService.updatePostSuccess(userDetails.getUser(), postId)
        );
    }

    @DeleteMapping("/{postId}") //할일카드 삭제(Test 완료)
    public ResponseEntity<PostResponse> deletePost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                           @PathVariable(name = "postId") Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                PostResponse.builder()
                        .post(postService.deletePost(userDetails.getUser(), postId))
                        .build()
        );
    }
}