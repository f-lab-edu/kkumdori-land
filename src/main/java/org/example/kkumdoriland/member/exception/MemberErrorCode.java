package org.example.kkumdoriland.member.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode {
    USER_EMAIL_DUPLICATION("User already exists with email"),
    USER_NOT_FOUND("User not found"),
    USER_PASSWORD_MISMATCH("Password does not match"),
    AUTHENTICATION_FAILED("Fail to authenticate");

    private final String message;
}
