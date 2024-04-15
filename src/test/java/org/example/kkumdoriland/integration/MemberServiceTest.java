package org.example.kkumdoriland.integration;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.example.kkumdoriland.member.application.MemberService;
import org.example.kkumdoriland.member.dto.MemberJoinDTO;
import org.example.kkumdoriland.member.dto.MemberResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberServiceTest extends IntegrationTestBase {
    @Autowired
    private MemberService userService;

    @Test
    void 회원가입__성공() {
        // given
        String name = "name";
        String email = "email@a.b";
        String password = "password";

        // when
        MemberResponse userId = userService.join(new MemberJoinDTO(name, email, password));

        // then
        assertThat(userId).isNotNull();
    }

    @Test
    void 회원가입__실패__중복된_이메일() {
        // given
        String name = "name";
        String email = "email@a.b";
        String password = "password";

        userService.join(new MemberJoinDTO(name, email, password));

        // when
        assertThatThrownBy(() -> userService.join(new MemberJoinDTO(name, email, password)));
    }
}
