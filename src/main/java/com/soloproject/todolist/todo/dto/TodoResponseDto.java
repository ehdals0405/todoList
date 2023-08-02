package com.soloproject.todolist.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class TodoResponseDto {
    private Long todoId;
    private String title;
    private int todoOrder;
    private boolean completed;
}
