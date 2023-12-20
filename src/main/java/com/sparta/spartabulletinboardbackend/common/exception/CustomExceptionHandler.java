package com.sparta.spartabulletinboardbackend.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    //기본 CustomeException
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomExceptionResponse> handleCustomException(CustomException e) {
        return ResponseEntity.status(e.getStatus()).body(new CustomExceptionResponse(e.getErrorCode(), e.getMessage()));
    }

    //입력값 검증 실패
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(e.getStatusCode()).body(
                        new CustomExceptionResponse(
                                CustomErrorCode.INVALID_INPUT_VALUE,
                                CustomErrorCode.INVALID_INPUT_VALUE.getStatusMessage()
                        ));
    }
}
