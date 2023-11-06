package com.sparta.spartabulletinboardbackend.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(CustomException e) {
        return ResponseEntity.status(e.getStatus()).body(new ExceptionResponse(e.getErrorCode(), e.getMessage()));
    }
}
