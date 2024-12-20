package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoRepositoryCustom {

    @Query("""
        SELECT t FROM Todo t
        WHERE (:weather IS NULL OR t.weather = :weather)
        AND (:startCreatedAt IS NULL OR t.createdAt >= :startCreatedAt)
        AND (:endCreatedAt IS NULL OR t.createdAt <= :endCreatedAt)
        ORDER BY t.modifiedAt DESC
    """)
    Page<Todo> findAllByWeatherAndCreatedAtRange(
            @Param("weather") String weather,
            @Param("startCreatedAt") LocalDateTime startCreatedAt,
            @Param("endCreatedAt") LocalDateTime endCreatedAt,
            Pageable pageable
    );

/*    @Query("SELECT t FROM Todo t " +
            "LEFT JOIN t.user " +
            "WHERE t.id = :todoId")
    Optional<Todo> findByIdWithUser(@Param("todoId") Long todoId);*/
}
