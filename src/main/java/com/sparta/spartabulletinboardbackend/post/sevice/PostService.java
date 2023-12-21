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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "PostService")
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Post savePost(User user, PostCreateRequest request) {
        Post post = Post.builder()
                .user(user)
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        postRepository.save(post);
        return post;
    }

    public List<PostReadAllResponse> readAllPost() {
        List<Post> posts = postRepository.findAllPostWithUser(); //1번
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
        return postRepository.findById(postId)
            .orElseThrow(() -> new CustomException(CustomErrorCode.POST_NOT_EXIST_EXCEPTION, 404));
    }

    @Transactional
    public Post updatePost(User user, Long postId, PostUpdateRequest request) {
        Post findPost = getUserPost(user, postId);

        return findPost.update(request);
    }

    @Transactional
    public Boolean updatePostSuccess(User user, Long postId) {
        Post findPost = getUserPost(user, postId);

        return findPost.updateSuccess();
    }

    @Transactional
    public Post deletePost(User user, Long postId) {
        Post findPost = getUserPost(user, postId);

        postRepository.delete(findPost);
        return findPost;
    }

    private Post getUserPost(User user, Long postId) {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.POST_NOT_EXIST_EXCEPTION, 404));

        if(!Objects.equals(user.getEmail(), findPost.getUser().getEmail()))
            throw new CustomException(CustomErrorCode.NOT_ALLOWED_TO_DELETE_POST_EXCEPTION, 403);

        return findPost;
    }
}