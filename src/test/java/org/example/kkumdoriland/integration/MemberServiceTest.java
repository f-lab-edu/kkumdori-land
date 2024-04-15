package org.example.kkumdoriland.integration;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import jdk.jshell.spi.ExecutionControl.UserException;
import org.example.kkumdoriland.member.application.MemberService;
import org.example.kkumdoriland.member.dto.MemberJoinDTO;
import org.example.kkumdoriland.member.dto.MemberLoginDTO;
import org.example.kkumdoriland.member.dto.MemberResponse;
import org.example.kkumdoriland.member.exception.MemberException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberServiceTest extends IntegrationTestBase {
    @Autowired
    private MemberService userService;

    private final String name = "name";
    private final String email = "email@a.b";
    private final String password = "password";

    @Test
    void 회원가입__성공() {
        // given

        // when
        final MemberResponse userId = userService.join(new MemberJoinDTO(name, email, password));

        // then
        assertThat(userId).isNotNull();
    }

    @Test
    void 회원가입__실패__중복된_이메일() {
        // given
        userService.join(new MemberJoinDTO(name, email, password));

        // when, then
        assertThatThrownBy(() -> userService.join(new MemberJoinDTO(name, email, password)));
    }

    @Test
    void 로그인__성공() {
        // given
        final MemberResponse createdUser = userService.join(new MemberJoinDTO(name, email, password));

        // when
        final MemberResponse user = userService.login(new MemberLoginDTO(email, password));

        // then
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(createdUser.getId());
    }

    @Test
    void 로그인__실패__사용자_없음() {
        // given
        userService.join(new MemberJoinDTO(name, email, password));

        final String fakeEmail = email + "hello";

        // when, then
        assertThatThrownBy(() -> userService.login(new MemberLoginDTO(fakeEmail, password)))
            .isInstanceOf(MemberException.class);
    }

    @Test
    void 로그인__실패__비밀번호_틀림() {
        // given
        userService.join(new MemberJoinDTO(name, email, password));

        final String wrongPassword = password + "hello";

        // when, then
        assertThatThrownBy(() -> userService.login(new MemberLoginDTO(email, wrongPassword)))
            .isInstanceOf(MemberException.class);
    }
}
