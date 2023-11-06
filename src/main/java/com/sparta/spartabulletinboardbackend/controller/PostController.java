package com.sparta.spartabulletinboardbackend.controller;

import com.sparta.spartabulletinboardbackend.dto.post.PostCreateRequest;
import com.sparta.spartabulletinboardbackend.dto.post.PostReadResponse;
import com.sparta.spartabulletinboardbackend.dto.post.PostUpdateRequest;
import com.sparta.spartabulletinboardbackend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/")
    public PostReadResponse createPost(@RequestBody PostCreateRequest request) {
        return new PostReadResponse(postService.savePost(request));
    }

    @GetMapping("/")
    public List<PostReadResponse> readPostAll() {
        return postService.readPostAll()
                .stream().map(PostReadResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{postId}")
    public PostReadResponse readPost(@PathVariable(name = "postId") Long postId) {
        return new PostReadResponse(postService.readPost(postId));
    }

    @PutMapping("/{postId}")
    public PostReadResponse updatePost(@PathVariable(name = "postId") Long postId,
                                       @RequestBody PostUpdateRequest request) {
        return new PostReadResponse(postService.updatePost(request, postId));
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable(name = "postId") Long postId) {
        postService.deletePost(postId);
    }

    @GetMapping("/password/{postId}")
    public boolean validatePassword(@PathVariable(name = "postId") Long postId,
                                    @RequestParam(name = "password") String password) {
        return postService.validatePassword(postId, password);
    }
}