package org.example.kkumdoriland.integration;

import org.example.kkumdoriland.dream.dto.DreamCreateDTO;
import org.example.kkumdoriland.dream.dto.DreamResponse;
import org.example.kkumdoriland.dream.dto.MileStoneCreateDTO;
import org.example.kkumdoriland.dream.dto.MileStoneResponse;
import org.example.kkumdoriland.dream.service.DreamService;
import org.example.kkumdoriland.dream.service.MileStoneService;
import org.example.kkumdoriland.member.dto.MemberJoinDTO;
import org.example.kkumdoriland.member.dto.MemberResponse;
import org.example.kkumdoriland.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ActiveProfiles("test")
public class MileStoneServiceTest extends IntegrationTestBase {
    @Autowired
    private DreamService dreamService;
    @Autowired
    private MileStoneService mileStoneService;
    @Autowired
    private MemberService memberService;

    private final String email = "email@a.b";
    private final String password = "password";
    private final String name = "name";

    private Long memberId;

    @BeforeEach
    void memberSetup() {
        final MemberResponse response = memberService.join(new MemberJoinDTO(email, password, name));
        memberId = response.getId();
    }


    @Test
    void 마일스톤_생성() {
        // given
        final DreamCreateDTO dto = new DreamCreateDTO("title", "description", "2022-12-31");
        final DreamResponse dream = dreamService.createDream(memberId, dto);
        final MileStoneCreateDTO milestoneDto = new MileStoneCreateDTO(dream.getId(), "title", "description", 10, 10);

        // when
        final MileStoneResponse milestone = mileStoneService.createMilestone(memberId, milestoneDto);

        // then
        assertThat(milestone).isNotNull();
    }

    @Test
    void 마일스톤_생성__권한오류() {
        // given
        final DreamCreateDTO dto = new DreamCreateDTO("title", "description", "2022-12-31");
        final DreamResponse dream = dreamService.createDream(memberId, dto);
        final MileStoneCreateDTO milestoneDto = new MileStoneCreateDTO(dream.getId(), "title", "description", 10, 10);

        assertThatThrownBy(() -> mileStoneService.createMilestone(memberId + 1, milestoneDto)).hasMessageStartingWith("권한");
    }
}
