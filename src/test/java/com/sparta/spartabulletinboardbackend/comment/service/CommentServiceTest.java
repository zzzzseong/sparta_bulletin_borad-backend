package com.sparta.spartabulletinboardbackend.comment.service;

import com.sparta.spartabulletinboardbackend.comment.dto.CommentCreateRequest;
import com.sparta.spartabulletinboardbackend.comment.entity.Comment;
import com.sparta.spartabulletinboardbackend.comment.repository.CommentRepository;
import com.sparta.spartabulletinboardbackend.post.entity.Post;
import com.sparta.spartabulletinboardbackend.post.repository.PostRepository;
import com.sparta.spartabulletinboardbackend.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;


@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock CommentRepository commentRepository;
    @Mock PostRepository postRepository;

    @Nested
    @DisplayName("댓글 생성 서비스 테스트")
    public class CreateCommentTest {
        //CommentService.saveComment()의 역할
        //1. 전달받은 정보를 바탕으로 Comment 객체를 생성해 repository 메서드에 전달한다 - 해당 테스트에서는 Comment 객체가 잘 생성되는지 확인

        @Test
        @DisplayName("댓글 생성 성공")
        public void createCommentSuccess() {
            //given
            User user = User.builder().build();

            Long postId = 100L;
            Post post = Post.builder().build();

            CommentCreateRequest request = new CommentCreateRequest();
            request.setComment("comment");

            given(postRepository.findById(postId)).willReturn(Optional.of(post));

            CommentService commentService = new CommentService(commentRepository, postRepository);

            //when
            Comment comment = commentService.saveComment(user, postId, request);

            //then
            assertEquals(request.getComment(), comment.getComment());
        }
    }
}