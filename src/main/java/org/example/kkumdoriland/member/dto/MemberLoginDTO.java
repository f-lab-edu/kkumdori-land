package org.example.kkumdoriland.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberLoginDTO {
    private String email;
    private String password;
}
