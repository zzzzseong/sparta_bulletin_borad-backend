package com.sparta.spartabulletinboardbackend.service;

import com.sparta.spartabulletinboardbackend.domain.Post;
import com.sparta.spartabulletinboardbackend.dto.post.PostCreateRequest;
import com.sparta.spartabulletinboardbackend.dto.post.PostUpdateRequest;
import com.sparta.spartabulletinboardbackend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Post savePost(PostCreateRequest request) {
        Post post = new Post(
                request.getTitle(),
                request.getAuthor(),
                request.getPassword(),
                request.getDescription()
        );

        postRepository.save(post);
        return post;
    }

    public List<Post> readPostAll() {
        return postRepository.findAll();
    }

    public Post readPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("postID가 잘못되었습니다."));
    }

    @Transactional
    public Post updatePost(PostUpdateRequest request, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("postID가 잘못되었습니다."));

        post.update(request);
        return post;
    }

    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException(""));

        postRepository.delete(post);
    }

    public boolean validatePassword(Long postId, String password) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("postID가 잘못되었습니다."));

        return post.comparePassword(password);
    }
}