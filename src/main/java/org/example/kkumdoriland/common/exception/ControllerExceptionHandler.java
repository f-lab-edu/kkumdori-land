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
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e);
    }
}
