package org.example.kkumdoriland.integration;

import org.example.kkumdoriland.dream.dto.*;
import org.example.kkumdoriland.dream.service.DailyHistoryService;
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

@ActiveProfiles("test")
public class DailyHistoryServiceTest extends IntegrationTestBase {
    @Autowired
    private DreamService dreamService;
    @Autowired
    private MileStoneService mileStoneService;
    @Autowired
    private DailyHistoryService dailyHistoryService;

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
    void 하루기록_생성() {
        // given
        final DreamCreateDTO dto = new DreamCreateDTO("title", "description", "2022-12-31");
        final DreamResponse dream = dreamService.createDream(memberId, dto);
        final MileStoneCreateDTO milestoneDto = new MileStoneCreateDTO(dream.getId(), "title", "description", 10, 10);
        final MileStoneResponse milestone = mileStoneService.createMilestone(memberId, milestoneDto);

        // when
        final DailyHistoryResponse createdStep = dailyHistoryService.createDailyHistory(memberId, new DailyHistoryCreateDTO("content", 10, milestone.getId()));

        // then
        assertThat(createdStep).isNotNull();
    }
}
