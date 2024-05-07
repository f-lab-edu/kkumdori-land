package org.example.kkumdoriland.dream.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.example.kkumdoriland.dream.domain.Dream;
import org.example.kkumdoriland.dream.domain.MileStone;
import org.example.kkumdoriland.dream.dto.DreamCreateDTO;
import org.example.kkumdoriland.dream.dto.DreamResponse;
import org.example.kkumdoriland.dream.dto.MileStoneCreateDTO;
import org.example.kkumdoriland.dream.dto.MileStoneResponse;
import org.example.kkumdoriland.dream.repository.DreamRepository;
import org.example.kkumdoriland.dream.repository.MileStoneRepository;
import org.example.kkumdoriland.member.domain.Member;
import org.example.kkumdoriland.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DreamService {
    private final DreamRepository dreamRepository;
    private final MemberRepository memberRepository;
    private final MileStoneRepository mileStoneRepository;
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public DreamResponse getDream(Long dreamId) {
        final Dream dream = dreamRepository.getReferenceById(dreamId);

        return DreamResponse.of(dream);
    }

    @Transactional
    public DreamResponse createDream(Long memberId, DreamCreateDTO dto) {
        final Member member = memberRepository.findMemberById(memberId).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        final Dream dream = Dream.builder()
            .user(member)
            .title(dto.getTitle())
            .description(dto.getDescription())
            .dueDate(LocalDate.parse(dto.getDueDate(), dateFormatter).atStartOfDay())
            .build();

        final Dream savedDream = dreamRepository.save(dream);

        return DreamResponse.of(savedDream);
    }

    @Transactional
    public MileStoneResponse createMilestone(Long memberId, MileStoneCreateDTO dto) {
        final Dream dream = dreamRepository.getReferenceById(dto.getDreamId());

        if (!dream.getUser().getId().equals(memberId)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }

        final MileStone mileStone = MileStone.builder()
            .dream(dream)
            .title(dto.getTitle())
            .description(dto.getDescription())
            .totalScoreToEarn(dto.getTotalScoreToEarn())
            .minimumStepSize(dto.getMinimumStepSize())
            .build();

        return MileStoneResponse.of(mileStoneRepository.save(mileStone));
    }
}
