package com.sparta.spartabulletinboardbackend.comment.entity;

import com.sparta.spartabulletinboardbackend.post.entity.Post;
import com.sparta.spartabulletinboardbackend.user.entity.User;
import com.sparta.spartabulletinboardbackend.user.entity.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class CommentTest {

    @Test
    @DisplayName("댓글 업데이트")
    public void updateComment() {
        //given
        User user = new User("username", "email", "password", UserRole.USER, null);
        Post post = new Post(user, "title", "content");

        User commentUser = new User("comment username", "comment email", "comment password", UserRole.USER, null);
        Comment comment = new Comment(commentUser, post, "comment");

        String newComment = "new comment";

        //when
        comment.update("new comment");

        //then
        assertEquals(newComment, comment.getComment());
    }
}