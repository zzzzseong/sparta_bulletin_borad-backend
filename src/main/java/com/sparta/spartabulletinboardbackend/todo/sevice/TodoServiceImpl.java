package com.sparta.spartabulletinboardbackend.todo.sevice;

import com.sparta.spartabulletinboardbackend.common.consts.ServiceConst;
import com.sparta.spartabulletinboardbackend.common.exception.CustomErrorCode;
import com.sparta.spartabulletinboardbackend.common.exception.CustomException;
import com.sparta.spartabulletinboardbackend.todo.dto.TodoRequest;
import com.sparta.spartabulletinboardbackend.todo.dto.TodoResponse;
import com.sparta.spartabulletinboardbackend.todo.entity.Todo;
import com.sparta.spartabulletinboardbackend.todo.repository.TodoQueryRepositoryImpl;
import com.sparta.spartabulletinboardbackend.todo.repository.TodoRepository;
import com.sparta.spartabulletinboardbackend.todo.repository.TodoSearchCond;
import com.sparta.spartabulletinboardbackend.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "PostService")
@Transactional(readOnly = true)
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final TodoQueryRepositoryImpl todoQueryRepository;

    @Override
    @Transactional
    public TodoResponse saveTodo(User user, TodoRequest request) {
        Todo todo = Todo.builder()
                .user(user)
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        todoRepository.save(todo);
        return TodoResponse.builder()
                .todo(todo)
                .build();
    }

    @Override
    public Map<String, List<TodoResponse>> readAllTodo() {
        Map<String, List<TodoResponse>> userTodoMap = new HashMap<>();

        //작성일 기준 내림차순
        List<Todo> todos = todoRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

        for(Todo todo : todos) {
            String username = todo.getUser().getUsername();
            TodoResponse todoResponse = TodoResponse.builder().todo(todo).build();

            if(userTodoMap.containsKey(username)) userTodoMap.get(username).add(todoResponse);
            else userTodoMap.put(username, List.of(todoResponse));
        }

        return userTodoMap;
    }

    @Override
    public TodoResponse readTodo(Long todoId) {
        return TodoResponse.builder()
                .todo(todoRepository.findById(todoId)
                        .orElseThrow(() -> new CustomException(CustomErrorCode.POST_NOT_EXIST_EXCEPTION, 404)))
                .build();
    }

    @Override
    @Transactional
    public Todo updateTodo(User user, TodoRequest request, Long todoId) {
        Todo findTodo = getUserTodo(user, todoId);

        return findTodo.update(request);
    }

    @Override
    @Transactional
    public Boolean updateTodoSuccess(User user, Long todoId) {
        Todo findTodo = getUserTodo(user, todoId);

        return findTodo.updateSuccess();
    }

    @Override
    @Transactional
    public Todo deleteTodo(User user, Long todoId) {
        Todo findTodo = getUserTodo(user, todoId);

        todoRepository.delete(findTodo);
        return findTodo;
    }

    public List<Todo> searchTodo(String keyword, int page) {
        TodoSearchCond cond = new TodoSearchCond(keyword);

        //최근 생성 게시물 내림차순
        Pageable pageable = PageRequest.of(page, ServiceConst.DEFAULT_BATCH_SIZE, Sort.by(Sort.Direction.DESC, "createdAt"));

        return todoQueryRepository.search(cond, pageable);
    }

    private Todo getUserTodo(User user, Long postId) {
        Todo findTodo = todoRepository.findById(postId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.POST_NOT_EXIST_EXCEPTION, 404));

        if(!Objects.equals(user.getEmail(), findTodo.getUser().getEmail()))
            throw new CustomException(CustomErrorCode.NOT_ALLOWED_TO_DELETE_POST_EXCEPTION, 403);

        return findTodo;
    }
}