package com.sparta.spartabulletinboardbackend.post.sevice;

import com.sparta.spartabulletinboardbackend.post.entity.Post;
import com.sparta.spartabulletinboardbackend.user.entity.User;
import com.sparta.spartabulletinboardbackend.post.dto.PostCreateRequest;
import com.sparta.spartabulletinboardbackend.post.dto.PostReadAllData;
import com.sparta.spartabulletinboardbackend.post.dto.PostReadAllResponse;
import com.sparta.spartabulletinboardbackend.post.dto.PostUpdateRequest;
import com.sparta.spartabulletinboardbackend.common.exception.CustomErrorCode;
import com.sparta.spartabulletinboardbackend.common.exception.CustomException;
import com.sparta.spartabulletinboardbackend.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public List<PostReadAllResponse> readAllPost() {
        List<Post> posts = postRepository.findAllPostWithUser();
        List<PostReadAllResponse> response = new ArrayList<>();

        String username = "";
        int responseIndex = -1;
        for (Post post : posts) {
            String curUsername = post.getUser().getUsername();

            if (!Objects.equals(username, curUsername)) {
                username = post.getUser().getUsername();
                response.add(PostReadAllResponse.builder()
                        .username(username)
                        .posts(new ArrayList<>())
                        .build());
                responseIndex++;
            }

            response.get(responseIndex).getPosts().add(new PostReadAllData(post));
        }

        return response;
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