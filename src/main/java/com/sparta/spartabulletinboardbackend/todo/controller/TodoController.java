package com.sparta.spartabulletinboardbackend.todo.controller;

import com.sparta.spartabulletinboardbackend.comment.dto.CommentResponse;
import com.sparta.spartabulletinboardbackend.comment.service.CommentServiceImpl;
import com.sparta.spartabulletinboardbackend.todo.dto.TodoListResponse;
import com.sparta.spartabulletinboardbackend.todo.dto.TodoRequest;
import com.sparta.spartabulletinboardbackend.todo.dto.TodoResponse;
import com.sparta.spartabulletinboardbackend.todo.sevice.TodoServiceImpl;
import com.sparta.spartabulletinboardbackend.user.entity.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoServiceImpl todoService;
    private final CommentServiceImpl commentService;

    @PostMapping("") //할일카드 작성(Test 완료)
    public ResponseEntity<TodoResponse> createTodo(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody TodoRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                TodoResponse.builder()
                    .todo(todoService.saveTodo(userDetails.getUser(), request))
                    .build()
        );
    }

    @GetMapping("") //할일카드 목록 조회(Test 완료)
    public ResponseEntity<List<TodoListResponse>> readTodoAll() {
        List<TodoListResponse> response = new ArrayList<>();

        Map<String, List<TodoResponse>> userPostMap = todoService.readAllTodo();

        userPostMap.forEach((key, value) -> response.add(new TodoListResponse(key, value)));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{todoId}") //할일카드 단일 조회(Test 완료)
    public ResponseEntity<TodoResponse> readTodo(@PathVariable(name = "todoId") Long todoId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                TodoResponse.builder()
                    .todo(todoService.readTodo(todoId))
                    .build()
        );
    }

    @PutMapping("/{todoId}") //할일카드 수정(Test 완료)
    public ResponseEntity<TodoResponse> updateTodo(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                   @RequestBody TodoRequest request,
                                                   @PathVariable(name = "todoId") Long todoId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                TodoResponse.builder()
                    .todo(todoService.updateTodo(userDetails.getUser(), request, todoId))
                    .build()
        );
    }

    @DeleteMapping("/{todoId}") //할일카드 삭제(Test 완료)
    public ResponseEntity<TodoResponse> deleteTodo(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                   @PathVariable(name = "todoId") Long todoId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                TodoResponse.builder()
                        .todo(todoService.deleteTodo(userDetails.getUser(), todoId))
                        .build()
        );
    }

    @GetMapping("/{todoId}/comment/") //할일카드 댓글 조회
    public ResponseEntity<List<CommentResponse>> readComment(
            @PathVariable(name = "todoId") Long todoId,
            @RequestParam(name = "page") int page
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                commentService.readComment(todoId, page).stream()
                        .map(CommentResponse::new).toList()
        );
    }

    @GetMapping("/success/{todoId}") //할일카드 완료(Test 완료)
    public ResponseEntity<Boolean> updateTodoSuccess(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                 @PathVariable(name = "todoId") Long todoId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                todoService.updateTodoSuccess(userDetails.getUser(), todoId)
        );
    }

    @GetMapping("/search") //할일카드 검색
    public ResponseEntity<List<TodoResponse>> searchTodo(
            @RequestParam(name = "keyword") String keyword,
            @RequestParam(name = "page") int page
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                todoService.searchTodo(keyword, page).stream()
                        .map(TodoResponse::new).toList()
        );
    }
}