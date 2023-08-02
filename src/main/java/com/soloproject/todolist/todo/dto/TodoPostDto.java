package com.soloproject.todolist.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;


@AllArgsConstructor
@Setter
@Getter
public class TodoPostDto {
    @NotBlank(message = "제목은 빈 칸이 아니어야 합니다.")
    private String title;
    @Positive(message = "1 이상의 수를 입력해주세요.")
    private int todoOrder;
    private boolean completed;
}
