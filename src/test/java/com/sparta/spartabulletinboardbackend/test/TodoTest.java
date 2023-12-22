package com.sparta.spartabulletinboardbackend.test;

import com.sparta.spartabulletinboardbackend.todo.dto.TodoRequest;
import com.sparta.spartabulletinboardbackend.todo.entity.Todo;

public interface TodoTest extends CommonTest {
    Long TEST_TODO_ID = 1L;
    String TEST_TODO_TITLE = "title";
    String TEST_TODO_CONTENT = "content";

    //TEST_TODO DTO
    TodoRequest TEST_TODO_REQUEST_DTO = TodoRequest.builder()
            .title(TEST_TODO_TITLE)
            .content(TEST_TODO_CONTENT)
            .build();
    TodoRequest TEST_TODO_RESPONSE_DTO = TodoRequest.builder()
            .title(TEST_TODO_TITLE)
            .content(TEST_TODO_CONTENT)
            .build();

    //TEST_TODO
    Todo TEST_TODO = Todo.builder()
            .user(TEST_USER)
            .title(TEST_TODO_TITLE)
            .content(TEST_TODO_CONTENT)
            .build();
    Todo TEST_ANOTHER_TODO = Todo.builder()
            .user(TEST_ANOTHER_USER)
            .title(ANOTHER_PREFIX + TEST_TODO_TITLE)
            .content(ANOTHER_PREFIX + TEST_TODO_CONTENT)
            .build();
}
