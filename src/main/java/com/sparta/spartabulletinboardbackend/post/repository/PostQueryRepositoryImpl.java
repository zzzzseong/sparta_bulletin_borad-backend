package com.sparta.spartabulletinboardbackend.post.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.spartabulletinboardbackend.post.entity.Post;
import com.sparta.spartabulletinboardbackend.post.entity.QPost;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostQueryRepositoryImpl implements PostQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);

    @Override
    public List<Post> search(PostSearchCond cond, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QPost.post.title.like(cond.getKeyword()));

        return jpaQueryFactory
                .select(QPost.post)
                .from(QPost.post)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}