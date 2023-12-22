package com.sparta.spartabulletinboardbackend.post.controller;

import com.sparta.spartabulletinboardbackend.comment.dto.CommentResponse;
import com.sparta.spartabulletinboardbackend.comment.service.CommentServiceImpl;
import com.sparta.spartabulletinboardbackend.post.dto.PostListResponse;
import com.sparta.spartabulletinboardbackend.post.dto.PostRequest;
import com.sparta.spartabulletinboardbackend.post.dto.PostResponse;
import com.sparta.spartabulletinboardbackend.post.sevice.PostServiceImpl;
import com.sparta.spartabulletinboardbackend.user.entity.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostServiceImpl postService;
    private final CommentServiceImpl commentService;

    @PostMapping("") //할일카드 작성(Test 완료)
    public ResponseEntity<PostResponse> createPost(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody PostRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                PostResponse.builder()
                    .post(postService.savePost(userDetails.getUser(), request))
                    .build()
        );
    }

    @GetMapping("") //할일카드 목록 조회(Test 완료)
    public ResponseEntity<List<PostListResponse>> readPostAll() {
        List<PostListResponse> response = new ArrayList<>();

        Map<String, List<PostResponse>> userPostMap = postService.readAllPost();

        userPostMap.forEach((key, value) -> response.add(new PostListResponse(key, value)));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{postId}") //할일카드 단일 조회(Test 완료)
    public ResponseEntity<PostResponse> readPost(@PathVariable(name = "postId") Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                PostResponse.builder()
                    .post(postService.readPost(postId))
                    .build()
        );
    }

    @PutMapping("/{postId}") //할일카드 수정(Test 완료)
    public ResponseEntity<PostResponse> updatePost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                       @RequestBody PostRequest request,
                                       @PathVariable(name = "postId") Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                PostResponse.builder()
                    .post(postService.updatePost(userDetails.getUser(), request, postId))
                    .build()
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

    @GetMapping("/{postId}/comment/") //할일카드 댓글 조회
    public ResponseEntity<List<CommentResponse>> readComment(
            @PathVariable(name = "postId") Long postId,
            @RequestParam(name = "page") int page
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                commentService.readComment(postId, page).stream()
                        .map(CommentResponse::new).toList()
        );
    }

    @GetMapping("/success/{postId}") //할일카드 완료(Test 완료)
    public ResponseEntity<Boolean> updateSuccess(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                 @PathVariable(name = "postId") Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                postService.updatePostSuccess(userDetails.getUser(), postId)
        );
    }

    @GetMapping("/search") //할일카드 검색
    public ResponseEntity<List<PostResponse>> searchPost(
            @RequestParam(name = "keyword") String keyword,
            @RequestParam(name = "page") int page
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                postService.searchPost(keyword, page).stream()
                        .map(PostResponse::new).toList()
        );
    }
}