package org.example.kkumdoriland.member.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberJoinDTO {
    private String name;
    private String email;
    private String password;
}
