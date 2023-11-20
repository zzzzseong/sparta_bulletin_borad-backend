package com.sparta.spartabulletinboardbackend.controller;

import com.sparta.spartabulletinboardbackend.dto.post.*;
import com.sparta.spartabulletinboardbackend.security.UserDetailsImpl;
import com.sparta.spartabulletinboardbackend.service.CommentService;
import com.sparta.spartabulletinboardbackend.service.PostService;
import lombok.RequiredArgsConstructor;
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
    public PostCreateResponse createPost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                       @RequestBody PostCreateRequest request) {
        return PostCreateResponse.builder()
                .post(postService.savePost(userDetails.getUser(), request))
                .build();
    }

    @GetMapping("") //할일카드 목록 조회(Test 완료)
    public List<PostReadAllResponse> readPostAll() {
        return postService.readAllPost();
    }

    @GetMapping("/{postId}") //할일카드 단일 조회(Test 완료)
    public PostReadResponse readPost(@PathVariable(name = "postId") Long postId) {
        return PostReadResponse.builder()
                .post(postService.readPost(postId))
                .comments(commentService.readAllCommentWithUserByPostId(postId))
                .build();
    }

    @PutMapping("/{postId}") //할일카드 수정(Test 완료)
    public PostUpdateResponse updatePost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                       @RequestBody PostUpdateRequest request,
                                       @PathVariable(name = "postId") Long postId) {
        return PostUpdateResponse.builder()
                .post(postService.updatePost(userDetails.getUser(), request, postId))
                .build();
    }

    @GetMapping("/success/{postId}") //할일카드 완료(Test 완료)
    public boolean updateSuccess(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                 @PathVariable(name = "postId") Long postId) {
        return postService.updatePostSuccess(userDetails.getUser(), postId);
    }

    @DeleteMapping("/{postId}") //할일카드 삭제(Test 완료)
    public void deletePost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                           @PathVariable(name = "postId") Long postId) {
        postService.deletePost(userDetails.getUser(), postId);
    }
}