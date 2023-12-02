package com.sparta.spartabulletinboardbackend.post.entity;

import com.sparta.spartabulletinboardbackend.post.dto.PostUpdateRequest;
import com.sparta.spartabulletinboardbackend.user.entity.User;
import com.sparta.spartabulletinboardbackend.user.entity.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class PostTest {

    @Test
    @DisplayName("TODO 업데이트")
    public void updateTODO() {
        //given
        User user = new User("username", "email", "password", UserRole.USER, null);
        Post post = new Post(user, "title", "content");

        PostUpdateRequest postUpdateRequest = new PostUpdateRequest();
        postUpdateRequest.setTitle("new title");
        postUpdateRequest.setContent("new content");

        //when
        post.update(postUpdateRequest);

        //then
        assertEquals(post.getTitle(), postUpdateRequest.getTitle());
        assertEquals(post.getContent(), postUpdateRequest.getContent());
    }

    @Test
    @DisplayName("TODO 완료기능 업데이트")
    public void updateTODOSuccess() {
        //given
        User user = new User("username", "email", "password", UserRole.USER, null);
        Post post = new Post(user, "title", "content");
        boolean success = post.isSuccess();

        //when
        post.updateSuccess();

        //then
        assertNotEquals(success, post.isSuccess());
        assertEquals(!success, post.isSuccess());
    }
}