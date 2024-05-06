package org.example.kkumdoriland.integration;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.example.kkumdoriland.member.service.MemberService;
import org.example.kkumdoriland.member.dto.MemberJoinDTO;
import org.example.kkumdoriland.member.dto.MemberResponse;
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
}
