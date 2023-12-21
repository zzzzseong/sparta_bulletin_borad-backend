package com.sparta.spartabulletinboardbackend.post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.spartabulletinboardbackend.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class PostQueryRepositoryImpl implements PostQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Page<Post> search(PostSearchCond cond, Pageable pageable) {
//        jpaQueryFactory.select(QPost.post)
//                .from(QPost.post)
//                .where(messageLike(cond.getKeyword()))

        return null;
    }
}
