package com.sparta.spartabulletinboardbackend.todo.repository;

import com.sparta.spartabulletinboardbackend.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("select p from Todo p join fetch p.user order by p.user.id, p.createdAt desc")
    List<Todo> findAllPostWithUser();
}