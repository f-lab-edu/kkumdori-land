package org.example.kkumdoriland.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.kkumdoriland.user.domain.User;

@Getter
@AllArgsConstructor
public class UserResponse {
    private final Long id;
    private final String name;

    public static UserResponse of(User user) {
        return new UserResponse(user.getId(), user.getName());
    }
}
