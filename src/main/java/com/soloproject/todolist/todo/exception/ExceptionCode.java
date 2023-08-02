package com.soloproject.todolist.todo.exception;

import lombok.Getter;


public enum ExceptionCode {
    NOT_FOUND_TODO("작성한 Todo가 없습니다."),
    ORDER_ERROR("중복된 순서 입력입니다.");

    @Getter
    private String message;

    ExceptionCode(String message){
        this.message = message;

    }

}
