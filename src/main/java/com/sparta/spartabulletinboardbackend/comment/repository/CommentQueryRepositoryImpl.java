package com.sparta.spartabulletinboardbackend.comment.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.spartabulletinboardbackend.comment.entity.Comment;
import com.sparta.spartabulletinboardbackend.comment.entity.QComment;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentQueryRepositoryImpl implements CommentQueryRepository {

    private final EntityManager em;

    @Override
    public List<Comment> readCommentAll(Long postId, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QComment.comment.post.id.eq(postId));

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);

        return jpaQueryFactory
                .select(QComment.comment)
                .from(QComment.comment)
                .where(builder)
                .innerJoin(QComment.comment.user).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
