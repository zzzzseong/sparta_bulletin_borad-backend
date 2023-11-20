package com.sparta.spartabulletinboardbackend.controller;

import com.sparta.spartabulletinboardbackend.dto.post.PostCreateRequest;
import com.sparta.spartabulletinboardbackend.dto.post.PostReadAllResponse;
import com.sparta.spartabulletinboardbackend.dto.post.PostReadResponse;
import com.sparta.spartabulletinboardbackend.dto.post.PostUpdateRequest;
import com.sparta.spartabulletinboardbackend.security.UserDetailsImpl;
import com.sparta.spartabulletinboardbackend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/") //할일카드 작성
    public PostReadResponse createPost(@RequestBody PostCreateRequest request,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return PostReadResponse.builder()
                .post(postService.savePost(userDetails.getUser(), request))
                .build();
    }

    @GetMapping("") //할일카드 목록 조회
    public PostReadAllResponse readPostAll() {
        return new PostReadAllResponse(postService.readAllPost());
    }

    @GetMapping("/{postId}") //할일카드 단일 조회
    public PostReadResponse readPost(@PathVariable(name = "postId") Long postId) {
        return PostReadResponse.builder()
                .post(postService.readPost(postId))
                .build();
    }

    @PutMapping("/{postId}") //할일카드 수정
    public PostReadResponse updatePost(@PathVariable(name = "postId") Long postId,
                                       @RequestBody PostUpdateRequest request) {
        return PostReadResponse.builder()
                .post(postService.updatePost(request, postId))
                .build();
    }

    @GetMapping("/success/{postId}") //할일카드 완료
    public boolean updateSuccess(@PathVariable(name = "postId") Long postId) {
        return postService.updatePostSuccess(postId);
    }

    @DeleteMapping("/{postId}") //할일카드 삭제
    public void deletePost(@PathVariable(name = "postId") Long postId) {
        postService.deletePost(postId);
    }
}