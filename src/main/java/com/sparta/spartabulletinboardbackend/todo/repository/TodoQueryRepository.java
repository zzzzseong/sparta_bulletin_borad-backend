package com.sparta.spartabulletinboardbackend.todo.repository;

import com.sparta.spartabulletinboardbackend.todo.entity.Todo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TodoQueryRepository {
    List<Todo> search(TodoSearchCond cond, Pageable pageable);
}
