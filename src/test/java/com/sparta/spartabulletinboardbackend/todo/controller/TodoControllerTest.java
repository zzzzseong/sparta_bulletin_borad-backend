package com.sparta.spartabulletinboardbackend.todo.controller;

import com.sparta.spartabulletinboardbackend.comment.service.CommentServiceImpl;
import com.sparta.spartabulletinboardbackend.test.ControllerTest;
import com.sparta.spartabulletinboardbackend.test.TodoTest;
import com.sparta.spartabulletinboardbackend.todo.dto.TodoRequest;
import com.sparta.spartabulletinboardbackend.todo.sevice.TodoServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @SpringBootTest 모든 Bean을 로드하기 때문에 테스트 구동시간 증가, 큰 단위로 인한 디버깅의 어려움을 겪을 수 있다.
 * @WebMvcTest Present Layer 관련 컴포넌트만 스캔하기 때문에 테스트 구동시간 감소, 작은 단위로 인한 디버깅의 용이성을 가진다. (슬라이스 테스트)
 * */
class TodoControllerTest extends ControllerTest implements TodoTest {

    //TodoController에서 사용하는 service bean을 @MockBean으로 주입해야한다.
    //해당하는 @MockBean을 선언해주지 bean을 찾을 수 없다는 에러가 발생함.
    @MockBean
    private TodoServiceImpl todoService;

    @MockBean
    private CommentServiceImpl commentService;

    @Test
    @DisplayName("TODO 생성 요청")
    void createTodoTest() throws Exception {
        //given

        //when
        ResultActions action = mockMvc.perform(post("/api/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(TEST_TODO_REQUEST_DTO))
        );

        //then
        action.andExpect(status().isCreated());
        verify(todoService, times(1)).saveTodo(eq(TEST_USER), any(TodoRequest.class));
    }
}