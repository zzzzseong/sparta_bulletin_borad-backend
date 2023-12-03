package com.sparta.spartabulletinboardbackend.post.sevice;

import com.sparta.spartabulletinboardbackend.common.exception.CustomErrorCode;
import com.sparta.spartabulletinboardbackend.common.exception.CustomException;
import com.sparta.spartabulletinboardbackend.post.dto.PostCreateRequest;
import com.sparta.spartabulletinboardbackend.post.dto.PostUpdateRequest;
import com.sparta.spartabulletinboardbackend.post.entity.Post;
import com.sparta.spartabulletinboardbackend.post.repository.PostRepository;
import com.sparta.spartabulletinboardbackend.user.entity.User;
import com.sparta.spartabulletinboardbackend.user.entity.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock PostRepository postRepository;

    @Test
    @DisplayName("TODO 생성(성공)")
    public void savePostTestSuccess() {
        //given
        User user = User.builder()
                .username("username")
                .email("email@email.com")
                .password("passwordA1~")
                .userRole(UserRole.USER)
                .build();

        PostCreateRequest request = new PostCreateRequest();
        request.setTitle("title");
        request.setContent("content");

        PostService postService = new PostService(postRepository);

        //when
        Post post = postService.savePost(user, request);

        //then
        assertEquals(request.getTitle(), post.getTitle());
        assertEquals(request.getContent(), post.getContent());
    }

    @Test
    @DisplayName("TODO 생성(실패) - 제목 없음")
    public void savePostTestTitleInvalid() {
        //given
        User user = User.builder()
                .username("username")
                .email("email@email.com")
                .password("passwordA1~")
                .userRole(UserRole.USER)
                .build();

        PostCreateRequest request = new PostCreateRequest();
        request.setTitle("");
        request.setContent("content");

        PostService postService = new PostService(postRepository);

        //when
        CustomException exception = assertThrows(CustomException.class, () -> postService.savePost(user, request));

        //then
        assertEquals(CustomErrorCode.POST_TITLE_INVALID_EXCEPTION, exception.getErrorCode());
    }

    @Test
    @DisplayName("TODO 단건 조회(실패) - TODO가 존재하지 않음")
    public void readPostTestNotExist() {
        //given
        Long postId = 100L;
        PostService postService = new PostService(postRepository);

        //when
        CustomException exception = assertThrows(CustomException.class, () -> postService.readPost(postId));

        //then
        assertEquals(CustomErrorCode.POST_NOT_EXIST_EXCEPTION, exception.getErrorCode());
    }

    @Test
    @DisplayName("TODO 수정(성공)")
    public void updatePostSuccess() {
        //given
        User user = User.builder()
                .username("username")
                .email("email@email.com")
                .password("passwordA1~")
                .userRole(UserRole.USER)
                .build();

        Post post = Post.builder()
                .user(user)
                .title("title")
                .content("content")
                .build();

        PostUpdateRequest request = new PostUpdateRequest();
        request.setTitle("title2");
        request.setContent("content2");

        Long postId = 100L;

        PostService postService = new PostService(postRepository);
        given(postRepository.findById(postId)).willReturn(Optional.of(post));

        //when
        Post updatePost = postService.updatePost(user, postId, request);

        //then
        assertEquals(request.getTitle(), updatePost.getTitle());
        assertEquals(request.getContent(), updatePost.getContent());
    }

    @Test
    @DisplayName("TODO 수정(실패) - TODO가 존재하지 않음")
    public void updatePostNotExist() {
        //given
        User user = User.builder()
                .username("username")
                .email("email@email.com")
                .password("passwordA1~")
                .userRole(UserRole.USER)
                .build();

        PostUpdateRequest request = new PostUpdateRequest();
        request.setTitle("title2");
        request.setContent("content2");

        Long postId = 100L;

        PostService postService = new PostService(postRepository);
        given(postRepository.findById(postId)).willReturn(Optional.empty());

        //when
        CustomException exception = assertThrows(CustomException.class, () -> postService.updatePost(user, postId, request));

        //then
        assertEquals(CustomErrorCode.POST_NOT_EXIST_EXCEPTION, exception.getErrorCode());
    }

//    @Test
//    @DisplayName("TODO 수정(실패) - 수정 권한이 없음")
//    public void updatePostNotAllow() {
//
//    }

}