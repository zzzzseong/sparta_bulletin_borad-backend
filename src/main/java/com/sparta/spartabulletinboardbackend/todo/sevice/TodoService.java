package com.sparta.spartabulletinboardbackend.todo.sevice;

import com.sparta.spartabulletinboardbackend.todo.dto.TodoRequest;
import com.sparta.spartabulletinboardbackend.todo.dto.TodoResponse;
import com.sparta.spartabulletinboardbackend.todo.entity.Todo;
import com.sparta.spartabulletinboardbackend.user.entity.User;

import java.util.List;
import java.util.Map;

public interface TodoService {

    /**
     * TODO 생성
     * @param user TODO 생성 요청자
     * @param request TODO 생성 요청 정보
     * @return TODO 생성 결과
     * */
    Todo saveTodo(User user, TodoRequest request);

    /**
     * TODO 전체 조회
     * @return TODO 전체 조회 결과
     * */
    Map<String, List<TodoResponse>> readAllTodo();

    /**
     * TODO 조회
     * @param todoId TODO 조회 ID
     * @return TODO 조회 결과
     * */
    Todo readTodo(Long todoId);

    /**
     * TODO 업데이트
     * @param user TODO 업데이트 요청자
     * @param todoId TODO 업데이트 ID
     * @param request TODO 업데이트 요청 정보
     * @return TODO 업데이트 결과
     * */
    Todo updateTodo(User user, TodoRequest request, Long todoId);

    /**
     * TODO 완료 업데이트
     * @param user TODO 완료 업데이트 요청자
     * @param todoId TODO 완료 업데이트 ID
     * @return TODO 완료 업데이트 결과
     * */
    Boolean updateTodoSuccess(User user, Long todoId);

    /**
     * TODO 삭제
     * @param user TODO 삭제 요청자
     * @param todoId TODO 삭제 ID
     * @return TODO 삭제 결과
     * */
    Todo deleteTodo(User user, Long todoId);

    /**
     * TODO 검색
     *
     * */
}
