package com.sparta.spartabulletinboardbackend.post.sevice;

import com.sparta.spartabulletinboardbackend.common.exception.CustomErrorCode;
import com.sparta.spartabulletinboardbackend.common.exception.CustomException;
import com.sparta.spartabulletinboardbackend.post.dto.PostCreateRequest;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

}