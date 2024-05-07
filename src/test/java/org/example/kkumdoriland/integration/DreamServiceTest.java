package org.example.kkumdoriland.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.example.kkumdoriland.dream.dto.DailyHistoryCreateDTO;
import org.example.kkumdoriland.dream.dto.DailyHistoryResponse;
import org.example.kkumdoriland.dream.dto.DreamCreateDTO;
import org.example.kkumdoriland.dream.dto.DreamResponse;
import org.example.kkumdoriland.dream.dto.MileStoneCreateDTO;
import org.example.kkumdoriland.dream.dto.MileStoneResponse;
import org.example.kkumdoriland.dream.service.DreamService;
import org.example.kkumdoriland.member.dto.MemberJoinDTO;
import org.example.kkumdoriland.member.dto.MemberResponse;
import org.example.kkumdoriland.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DreamServiceTest extends IntegrationTestBase {
    @Autowired
    private DreamService dreamService;
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
    void 꿈_생성() {
        // given
        final DreamCreateDTO dto = new DreamCreateDTO("title", "description", "2022-12-31");

        // when
        final DreamResponse dream = dreamService.createDream(memberId, dto);

        // then
        assertThat(dream).isNotNull();
    }

    @Test
    void 꿈_조회() {
        // given
        final DreamCreateDTO dto = new DreamCreateDTO("title", "description", "2022-12-31");
        final DreamResponse dream = dreamService.createDream(memberId, dto);

        // when
        final DreamResponse foundDream = dreamService.getDream(dream.getId());

        // then
        assertThat(foundDream).isNotNull();
    }

    @Test
    void 마일스톤_생성() {
        // given
        final DreamCreateDTO dto = new DreamCreateDTO("title", "description", "2022-12-31");
        final DreamResponse dream = dreamService.createDream(memberId, dto);
        final MileStoneCreateDTO milestoneDto = new MileStoneCreateDTO(dream.getId(), "title", "description", 10, 10);

        // when
        final MileStoneResponse milestone = dreamService.createMilestone(memberId, milestoneDto);

        // then
        assertThat(milestone).isNotNull();
    }

    @Test
    void 하루기록_생성() {
        // given
        final DreamCreateDTO dto = new DreamCreateDTO("title", "description", "2022-12-31");
        final DreamResponse dream = dreamService.createDream(memberId, dto);
        final MileStoneCreateDTO milestoneDto = new MileStoneCreateDTO(dream.getId(), "title", "description", 10, 10);
        final MileStoneResponse milestone = dreamService.createMilestone(memberId, milestoneDto);

        // when
        final DailyHistoryResponse createdStep = dreamService.createDailyHistory(memberId, new DailyHistoryCreateDTO("content", 10, milestone.getId()));

        // then
        assertThat(createdStep).isNotNull();
    }
}
