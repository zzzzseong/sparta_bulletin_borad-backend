package com.sparta.spartabulletinboardbackend.service;

import com.sparta.spartabulletinboardbackend.domain.Post;
import com.sparta.spartabulletinboardbackend.domain.user.User;
import com.sparta.spartabulletinboardbackend.dto.post.PostCreateRequest;
import com.sparta.spartabulletinboardbackend.dto.post.PostUpdateRequest;
import com.sparta.spartabulletinboardbackend.exception.CustomErrorCode;
import com.sparta.spartabulletinboardbackend.exception.CustomException;
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
    public Post savePost(User user, PostCreateRequest request) {
        return postRepository.save(new Post(user, request.getTitle(), request.getContent()));
    }

    public List<Post> readAllPost() {
        return postRepository.findAllPostWithUsername();
    }


    public Post readPost(Long postId) {
        return postRepository.findPostWithUsernameById(postId)
            .orElseThrow(() -> new CustomException(CustomErrorCode.POST_NOT_FOUND_EXCEPTION, 404));
    }

    @Transactional
    public Post updatePost(PostUpdateRequest request, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.POST_NOT_FOUND_EXCEPTION, 404));

        return post.update(request);
    }

    @Transactional
    public boolean updatePostSuccess(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.POST_NOT_FOUND_EXCEPTION, 404));

        return post.updateSuccess();
    }

    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.POST_NOT_FOUND_EXCEPTION, 404));

        postRepository.delete(post);
    }
}