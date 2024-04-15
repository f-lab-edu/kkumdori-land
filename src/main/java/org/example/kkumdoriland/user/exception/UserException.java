package org.example.kkumdoriland.user.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserException extends RuntimeException {
    private final UserErrorCode errorCode;
    private final String message;
}
