package com.sparta.spartabulletinboardbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {

    POST_NOT_FOUND_EXCEPTION("게시글이 존재하지 않습니다");

    private final String statusMessage;
}
