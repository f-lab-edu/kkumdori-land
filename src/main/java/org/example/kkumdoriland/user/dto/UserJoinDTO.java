package org.example.kkumdoriland.user.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kkumdoriland.user.domain.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinDTO {
    private String name;
    private String email;
    private String password;

    public User toUser() {
         return new User(name, email, password);
    }
}
