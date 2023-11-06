package com.sparta.spartabulletinboardbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    private CustomErrorCode errorCode;
    private String statusMessage;
}