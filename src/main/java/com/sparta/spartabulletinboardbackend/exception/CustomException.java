package com.sparta.spartabulletinboardbackend.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private CustomErrorCode errorCode;
    private String message;
    private int status;

    public CustomException(CustomErrorCode errorCode, int status) {
        super(errorCode.getStatusMessage());
        this.errorCode = errorCode;
        this.message = errorCode.getStatusMessage();
        this.status = status;
    }

}
