package com.sparta.spartabulletinboardbackend.post.sevice;

import com.sparta.spartabulletinboardbackend.common.consts.ServiceConst;
import com.sparta.spartabulletinboardbackend.common.exception.CustomErrorCode;
import com.sparta.spartabulletinboardbackend.common.exception.CustomException;
import com.sparta.spartabulletinboardbackend.post.dto.PostRequest;
import com.sparta.spartabulletinboardbackend.post.dto.PostResponse;
import com.sparta.spartabulletinboardbackend.post.entity.Post;
import com.sparta.spartabulletinboardbackend.post.repository.PostQueryRepositoryImpl;
import com.sparta.spartabulletinboardbackend.post.repository.PostRepository;
import com.sparta.spartabulletinboardbackend.post.repository.PostSearchCond;
import com.sparta.spartabulletinboardbackend.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "PostService")
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostQueryRepositoryImpl postQueryRepository;

    @Transactional
    public Post savePost(User user, PostRequest request) {
        Post post = Post.builder()
                .user(user)
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        postRepository.save(post);
        return post;
    }

    public Map<String, List<PostResponse>> readAllPost() {
        Map<String, List<PostResponse>> userPostMap = new HashMap<>();

        //작성일 기준 내림차순
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

        for(Post post : posts) {
            String username = post.getUser().getUsername();
            PostResponse postResponse = PostResponse.builder().post(post).build();

            if(userPostMap.containsKey(username)) userPostMap.get(username).add(postResponse);
            else userPostMap.put(username, List.of(postResponse));
        }

        return userPostMap;
    }

    public Post readPost(Long postId) {
        return postRepository.findById(postId)
            .orElseThrow(() -> new CustomException(CustomErrorCode.POST_NOT_EXIST_EXCEPTION, 404));
    }

    @Transactional
    public Post updatePost(User user, PostRequest request, Long postId) {
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

    public List<Post> searchPost(String keyword, int page) {
        PostSearchCond cond = new PostSearchCond(keyword);

        //최근 생성 게시물 내림차순
        Pageable pageable = PageRequest.of(page, ServiceConst.DEFAULT_BATCH_SIZE, Sort.by(Sort.Direction.DESC, "createdAt"));

        return postQueryRepository.search(cond, pageable);
    }

    private Post getUserPost(User user, Long postId) {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.POST_NOT_EXIST_EXCEPTION, 404));

        if(!Objects.equals(user.getEmail(), findPost.getUser().getEmail()))
            throw new CustomException(CustomErrorCode.NOT_ALLOWED_TO_DELETE_POST_EXCEPTION, 403);

        return findPost;
    }
}