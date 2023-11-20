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
import java.util.Objects;

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
        return postRepository.findPostWithUserById(postId)
            .orElseThrow(() -> new CustomException(CustomErrorCode.POST_NOT_EXIST_EXCEPTION, 404));
    }

    @Transactional
    public Post updatePost(User user, PostUpdateRequest request, Long postId) {
        Post findPost = postRepository.findPostWithUserById(postId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.POST_NOT_EXIST_EXCEPTION, 404));
        if(!Objects.equals(user.getId(), findPost.getUser().getId()))
            throw new CustomException(CustomErrorCode.NOT_ALLOWED_TO_UPDATE_POST_EXCEPTION, 403);

        return findPost.update(request);
    }

    @Transactional
    public boolean updatePostSuccess(User user, Long postId) {
        Post findPost = postRepository.findPostWithUserById(postId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.POST_NOT_EXIST_EXCEPTION, 404));
        if(!Objects.equals(user.getId(), findPost.getUser().getId()))
            throw new CustomException(CustomErrorCode.NOT_ALLOWED_TO_UPDATE_POST_EXCEPTION, 403);

        return findPost.updateSuccess();
    }

    @Transactional
    public void deletePost(User user, Long postId) {
        Post findPost = postRepository.findPostWithUserById(postId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.POST_NOT_EXIST_EXCEPTION, 404));
        if(!Objects.equals(user.getId(), findPost.getUser().getId()))
            throw new CustomException(CustomErrorCode.NOT_ALLOWED_TO_DELETE_POST_EXCEPTION, 403);

        postRepository.delete(findPost);
    }
}