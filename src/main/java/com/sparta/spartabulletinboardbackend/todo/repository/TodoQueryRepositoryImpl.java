package com.sparta.spartabulletinboardbackend.todo.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.spartabulletinboardbackend.todo.entity.QTodo;
import com.sparta.spartabulletinboardbackend.todo.entity.Todo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoQueryRepositoryImpl implements TodoQueryRepository {

    private final EntityManager em;

    @Override
    public List<Todo> search(TodoSearchCond cond, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QTodo.todo.title.like(cond.getKeyword()));

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        return jpaQueryFactory
                .select(QTodo.todo)
                .from(QTodo.todo)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}