package com.soloproject.todolist.todo.advice;

import com.soloproject.todolist.todo.exception.BusinessLogicException;
import com.soloproject.todolist.todo.exception.ErrorResponse;
import com.soloproject.todolist.todo.exception.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler
    public ResponseEntity handleBusinessLogicException(BusinessLogicException e){
        System.out.println(e.getMessage());
        ErrorResponse response = ErrorResponse.of(e.getExceptionCode());

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}
