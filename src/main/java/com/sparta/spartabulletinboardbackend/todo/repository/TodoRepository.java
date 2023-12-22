package com.sparta.spartabulletinboardbackend.todo.repository;

import com.sparta.spartabulletinboardbackend.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}