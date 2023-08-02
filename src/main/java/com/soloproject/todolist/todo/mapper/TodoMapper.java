package com.soloproject.todolist.todo.mapper;

import com.soloproject.todolist.todo.dto.TodoPatchDto;
import com.soloproject.todolist.todo.dto.TodoPostDto;
import com.soloproject.todolist.todo.dto.TodoResponseDto;
import com.soloproject.todolist.todo.entity.Todo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    Todo todoPostDtoTotodo(TodoPostDto todoPostDto);

    Todo todoPatchDtoTotodo(TodoPatchDto patchDto);

    TodoResponseDto todoTotodoResponseDto(Todo todo);
}
