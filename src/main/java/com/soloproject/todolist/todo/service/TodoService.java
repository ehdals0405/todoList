package com.soloproject.todolist.todo.service;

import com.soloproject.todolist.todo.exception.BusinessLogicException;
import com.soloproject.todolist.todo.exception.ExceptionCode;
import com.soloproject.todolist.todo.entity.Todo;
import com.soloproject.todolist.todo.repository.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TodoService {
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todo) throws BusinessLogicException {
        List<Integer> todoList = todoRepository.findAll().stream()
                .map(todos -> todos.getTodoOrder())
                .collect(Collectors.toList());

        if (todoList.contains(todo.getTodoOrder())) {
            throw new BusinessLogicException(ExceptionCode.ORDER_ERROR);
        }
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Todo todo) {

        Todo findTodo = findVerityTodo(todo.getTodoId());

        findTodo.setTitle(todo.getTitle());
        findTodo.setCompleted(todo.isCompleted());

        return findTodo;
    }
    @Transactional(readOnly = true)
    public Todo getTodo(long todoId) {
        return findVerityTodo(todoId);
    }
    @Transactional(readOnly = true)
    public Page<Todo> getAllTodo(int page, int size) {
        return todoRepository.findAll(PageRequest.of(page - 1, size, Sort.by("todoOrder")));
    }
    @Transactional(readOnly = true)
    public void deleteTodo(long todoId) {
        findVerityTodo(todoId);
        todoRepository.deleteById(todoId);
    }
    @Transactional(readOnly = true)
    public void deleteAllTodo() {
        todoRepository.deleteAll();
    }

    public Todo findVerityTodo(long todoId) {
        Optional<Todo> optionalTodo = todoRepository.findById(todoId);
        Todo findTodo = optionalTodo.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_TODO));

        return findTodo;
    }
}
