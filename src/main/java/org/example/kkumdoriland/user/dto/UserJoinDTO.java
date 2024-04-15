package org.example.kkumdoriland.user.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.example.kkumdoriland.user.domain.User;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinDTO {
    private String name;
    private String email;
    private String password;
}
