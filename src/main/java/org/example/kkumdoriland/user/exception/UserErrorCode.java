package org.example.kkumdoriland.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode {
    USER_EMAIL_DUPLICATION(HttpStatus.CONFLICT, "User already exists with email");

    private final HttpStatus httpStatus;
    private final String message;
}
