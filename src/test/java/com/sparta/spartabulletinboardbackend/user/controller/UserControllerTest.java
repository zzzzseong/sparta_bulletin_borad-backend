package com.sparta.spartabulletinboardbackend.user.controller;

import com.sparta.spartabulletinboardbackend.test.ControllerTest;
import com.sparta.spartabulletinboardbackend.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.springframework.boot.test.mock.mockito.MockBean;

class UserControllerTest extends ControllerTest {

    @MockBean
    private UserService userService;

    @Nested
    @DisplayName("유저 회원가입")
    class test1 {
        //만약 내가 User 회원가입에 대한 테스트 코드를 짜고자 한다.
        //이래서 코드를 작성할때 미루미 말고 테스트 코드를 작성하라고 하는거구나..
    }
}

/*
* 		// given

		// when
		var action = mockMvc.perform(post("/api/todos")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(TEST_TODO_REQUEST_DTO)));

		// then
		action.andExpect(status().isCreated());
		verify(todoService, times(1)).createTodo(any(TodoRequestDTO.class), eq(TEST_USER));
* */