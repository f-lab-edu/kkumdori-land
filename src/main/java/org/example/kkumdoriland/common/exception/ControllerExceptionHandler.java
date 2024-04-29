package org.example.kkumdoriland.common.exception;

import org.example.kkumdoriland.member.exception.MemberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(MemberException.class)
    public ResponseEntity<MemberException> handleUserException(MemberException e) {
        return switch (e.getErrorCode()) {
            case USER_EMAIL_DUPLICATION -> ResponseEntity.status(HttpStatus.CONFLICT).body(e);
            case USER_NOT_FOUND -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
            case USER_PASSWORD_MISMATCH -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e);
            case AUTHENTICATION_FAILED -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        };
    }
}
