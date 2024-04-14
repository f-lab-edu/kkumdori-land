package org.example.kkumdoriland.common.exception;

import org.example.kkumdoriland.user.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<UserException> handleUserException(UserException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e);
    }
}
