package com.sparta.spartabulletinboardbackend.post.sevice;

import com.sparta.spartabulletinboardbackend.post.dto.PostRequest;
import com.sparta.spartabulletinboardbackend.post.dto.PostResponse;
import com.sparta.spartabulletinboardbackend.post.entity.Post;
import com.sparta.spartabulletinboardbackend.user.entity.User;

import java.util.List;
import java.util.Map;

public interface PostService {

    /**
     * TODO 생성
     * @param user TODO 생성 요청자
     * @param request TODO 생성 요청 정보
     * @return TODO 생성 결과
     * */
    Post savePost(User user, PostRequest request);

    /**
     * TODO 전체 조회
     * @return TODO 전체 조회 결과
     * */
    Map<String, List<PostResponse>> readAllPost();

    /**
     * TODO 조회
     * @param postId TODO 조회 ID
     * @return TODO 조회 결과
     * */
    Post readPost(Long postId);

    /**
     * TODO 업데이트
     * @param user TODO 업데이트 요청자
     * @param postId TODO 업데이트 ID
     * @param request TODO 업데이트 요청 정보
     * @return TODO 업데이트 결과
     * */
    Post updatePost(User user, PostRequest request, Long postId);

    /**
     * TODO 완료 업데이트
     * @param user TODO 완료 업데이트 요청자
     * @param postId TODO 완료 업데이트 ID
     * @return TODO 완료 업데이트 결과
     * */
    Boolean updatePostSuccess(User user, Long postId);

    /**
     * TODO 삭제
     * @param user TODO 삭제 요청자
     * @param postId TODO 삭제 ID
     * @return TODO 삭제 결과
     * */
    Post deletePost(User user, Long postId);

    /**
     * TODO 검색
     *
     * */
}
