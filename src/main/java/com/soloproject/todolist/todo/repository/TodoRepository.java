package com.soloproject.todolist.todo.repository;

import com.soloproject.todolist.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long> {
    Page<Todo> findAll(Pageable pageable);
}
