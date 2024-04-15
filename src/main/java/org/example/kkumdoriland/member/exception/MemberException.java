package org.example.kkumdoriland.member.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberException extends RuntimeException {
    private final MemberErrorCode errorCode;
    private final String message;
}
