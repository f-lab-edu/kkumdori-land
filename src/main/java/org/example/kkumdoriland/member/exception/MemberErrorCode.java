package org.example.kkumdoriland.member.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode {
    USER_EMAIL_DUPLICATION("User already exists with email");

    private final String message;
}
