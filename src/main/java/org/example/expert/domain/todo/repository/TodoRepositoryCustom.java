package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface TodoRepositoryCustom {
    Page<Todo> findAllByWeatherAndCreatedAtRange(String weather, LocalDateTime startCreatedAt, LocalDateTime endCreatedAt, Pageable pageable);
}

