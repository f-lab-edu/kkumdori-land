package org.example.kkumdoriland.user.unit;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.example.kkumdoriland.user.application.UserService;
import org.example.kkumdoriland.user.dto.UserJoinDTO;
import org.example.kkumdoriland.user.dto.UserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void 회원가입__성공() {
        // given
        String name = "name";
        String email = "email@a.b";
        String password = "password";

        // when
        UserResponse userId = userService.join(new UserJoinDTO(name, email, password));

        // then
        assertThat(userId).isNotNull();
    }

    @Test
    void 회원가입__실패__중복된_이메일() {
        // given
        String name = "name";
        String email = "email@a.b";
        String password = "password";

        userService.join(new UserJoinDTO(name, email, password));

        // when
        assertThatThrownBy(() -> userService.join(new UserJoinDTO(name, email, password)));
    }
}
