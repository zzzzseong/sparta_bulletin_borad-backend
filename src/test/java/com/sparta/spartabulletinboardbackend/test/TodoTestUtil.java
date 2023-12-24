package com.sparta.spartabulletinboardbackend.test;

import com.sparta.spartabulletinboardbackend.todo.entity.Todo;
import com.sparta.spartabulletinboardbackend.user.entity.User;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

public class TodoTestUtil {
    public static Todo get(Todo todo, User user, long id) {
        return get(todo, user, id, LocalDateTime.now());
    }

    /**
     * 테스트용 할일 객체를 만들어주는 메서드
     * @param todo       복제할 할일 객체
     * @param id         설정할 아이디
     * @param createdAt 설정할 생성일시
     * @param user       연관관계 유저
     * @return 테스트용으로 생성된 할일 객체
     */
    public static Todo get(Todo todo, User user, Long id, LocalDateTime createdAt) {
        ReflectionTestUtils.setField(todo, Todo.class, "id", id, Long.class);
        ReflectionTestUtils.setField(todo, Todo.class, "createdAt", createdAt, LocalDateTime.class);
        ReflectionTestUtils.setField(todo, Todo.class, "user", user, User.class);

        return todo;
    }
}
