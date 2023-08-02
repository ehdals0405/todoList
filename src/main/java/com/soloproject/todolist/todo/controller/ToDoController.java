package com.soloproject.todolist.todo.controller;

import com.soloproject.todolist.todo.dto.TodoPatchDto;
import com.soloproject.todolist.todo.dto.TodoPostDto;
import com.soloproject.todolist.todo.dto.TodoResponseDto;
import com.soloproject.todolist.todo.entity.Todo;
import com.soloproject.todolist.todo.mapper.TodoMapper;
import com.soloproject.todolist.todo.service.TodoService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/")
public class ToDoController {
    private TodoMapper mapper;
    private TodoService service;

    public ToDoController(TodoMapper mapper, TodoService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity postTodo(@RequestBody TodoPostDto todoPostDto) {
        Todo todo = mapper.todoPostDtoTotodo(todoPostDto);
        service.createTodo(todo);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchTodo(@PathVariable("id") @Positive long todoId,
                                    @RequestBody TodoPatchDto todoPatchDto) {
        Todo todo = mapper.todoPatchDtoTotodo(todoPatchDto);
        todo.setTodoId(todoId);
        service.updateTodo(todo);
        TodoResponseDto responseDto = mapper.todoTotodoResponseDto(todo);

        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getTodo(@PathVariable("id") @Positive long todoId) {

        Todo todo = service.getTodo(todoId);
        TodoResponseDto responseDto = mapper.todoTotodoResponseDto(todo);

        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTodos(@RequestParam @Positive int page,
                                   @RequestParam @Positive int size) {
        Page<Todo> pageTodos = service.getAllTodo(page, size);
        List<Todo> todos = pageTodos.getContent();

        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodo(@PathVariable("id") @Positive long todoId) {
        service.deleteTodo(todoId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity deleteTodos() {
        service.deleteAllTodo();

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
