package org.example.expert.domain.todo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public abstract class TodoRepositoryImpl implements TodoRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public TodoRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Todo> findAllByWeatherAndCreatedAtRange(String weather, LocalDateTime startCreatedAt, LocalDateTime endCreatedAt, Pageable pageable) {
        QTodo todo = QTodo.todo;
        QUser user = QUser.user;

        List<Todo> results = queryFactory.select(todo)
                .from(todo)
                .join(todo.user, user).fetchJoin()
                .where(
                        todo.weather.eq(weather),
                        todo.createdAt.between(startCreatedAt, endCreatedAt)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory.select(todo.count())
                .from(todo)
                .where(
                        todo.weather.eq(weather),
                        todo.createdAt.between(startCreatedAt, endCreatedAt)
                )
                .fetchOne();

        return new PageImpl<>(results, pageable, total);
    }
}

