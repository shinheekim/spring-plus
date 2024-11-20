package org.example.expert.domain.todo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.example.expert.domain.todo.entity.QTodo.todo;

@Repository
@RequiredArgsConstructor
public class TodoRepositoryCustomImpl implements TodoRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Todo> findByIdWithUser(Long todoId){
        Todo results = queryFactory
                .select(todo)
                .from(todo)
                .leftJoin(todo.user).fetchJoin()
                .where(todo.id.eq(todoId))
                .fetchOne();

        return Optional.ofNullable(results);
    }

/*    @Override
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
    }*/
}

