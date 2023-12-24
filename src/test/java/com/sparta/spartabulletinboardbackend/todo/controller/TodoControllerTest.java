package com.sparta.spartabulletinboardbackend.todo.controller;

import com.sparta.spartabulletinboardbackend.comment.service.CommentServiceImpl;
import com.sparta.spartabulletinboardbackend.test.ControllerTest;
import com.sparta.spartabulletinboardbackend.test.TodoTest;
import com.sparta.spartabulletinboardbackend.todo.dto.TodoRequest;
import com.sparta.spartabulletinboardbackend.todo.sevice.TodoServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @SpringBootTest 모든 Bean을 로드하기 때문에 테스트 구동시간 증가, 큰 단위로 인한 디버깅의 어려움을 겪을 수 있다.
 * @WebMvcTest Present Layer 관련 컴포넌트만 스캔하기 때문에 테스트 구동시간 감소, 작은 단위로 인한 디버깅의 용이성을 가진다. (슬라이스 테스트)
 */
class TodoControllerTest extends ControllerTest implements TodoTest {

    //TodoController에서 사용하는 service bean을 @MockBean으로 주입해야한다.
    //해당하는 @MockBean을 선언해주지 bean을 찾을 수 없다는 에러가 발생함.
    @MockBean
    private TodoServiceImpl todoService;

    @MockBean
    private CommentServiceImpl commentService;

    @Nested
    @DisplayName("TODO 생성 요청")
    class createTodoTest {

        /**
         * 기존 TodoService에서 Todo를 return했는데 SliceTest를 진행하려다보니 TodoResponse의 생성자로 매개변수인 Todo객체가 null로 전달되는 문제 발생
         * -> TodoService의 return 타입을 Todo에서 TodoResponse로 변경해서 해결 완료
         */
        @Test
        @DisplayName("TODO 생성 요청 성공")
        void createTodo_success() throws Exception {
            //given

            //when
            ResultActions action = mockMvc.perform(post("/api/todo")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(TEST_TODO_REQUEST))
            );

            //then
            action.andExpect(status().isCreated());

            //saveTodo로 전달되는 매개변수가 TEST_USER, TodoRequest와 일치하는지 검증
            verify(todoService, times(1)).saveTodo(eq(TEST_USER), any(TodoRequest.class));
        }

        @Test
        @DisplayName("TODO 생성 요청 실패 - 제목이 없는 경우")
        void createTodo_fail_invalidTitle() throws Exception {
            //given
            //제목이 없는 TodoRequest - @Valid 검증 실패
            TodoRequest request = TodoRequest.builder()
                    .title("")
                    .content("content")
                    .build();

            //when
            ResultActions action = mockMvc.perform(post("/api/todo")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            );

            //then
            action
                    .andExpect(status().isBadRequest())
                    .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
        }
    }

    @Test
    @DisplayName("TODO 단일 조회 요청 성공")
    void readTodo_success() throws Exception {
        //given
        given(todoService.readTodo(TEST_TODO_ID)).willReturn(TEST_TODO_RESPONSE);

        //when
        ResultActions action = mockMvc.perform(get("/api/todo/{todoId}", TEST_TODO_ID)
                .accept(MediaType.APPLICATION_JSON));

        //then
        action
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username").value(TEST_USER.getUsername()))
            .andExpect(jsonPath("$.title").value(TEST_TODO_TITLE))
            .andExpect(jsonPath("$.content").value(TEST_TODO_CONTENT));
    }

    @Test
    @DisplayName("TODO 목록 조회 요청 성공")
    void readTodoAll_success() throws Exception {
        //given
        given(todoService.readAllTodo()).willReturn(Map.of(
            TEST_USER.getUsername(), List.of(TEST_TODO_RESPONSE),
            TEST_ANOTHER_USER.getUsername(), List.of(TEST_ANOTHER_TODO_RESPONSE)
        ));

        //when
        ResultActions action = mockMvc.perform(get("/api/todo")
                .accept(MediaType.APPLICATION_JSON));

        //then
        action.andExpect(status().isOk());
    }
}