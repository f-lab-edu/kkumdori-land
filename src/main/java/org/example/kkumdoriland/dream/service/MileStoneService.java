package org.example.kkumdoriland.dream.service;

import lombok.RequiredArgsConstructor;
import org.example.kkumdoriland.dream.domain.Dream;
import org.example.kkumdoriland.dream.domain.MileStone;
import org.example.kkumdoriland.dream.dto.MileStoneCreateDTO;
import org.example.kkumdoriland.dream.dto.MileStoneResponse;
import org.example.kkumdoriland.dream.repository.DreamRepository;
import org.example.kkumdoriland.dream.repository.milestone.MileStoneRepository;
import org.example.kkumdoriland.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MileStoneService {

    private final MemberRepository memberRepository;
    private final DreamRepository dreamRepository;
    private final MileStoneRepository mileStoneRepository;

    @Transactional
    public MileStoneResponse createMilestone(Long memberId, MileStoneCreateDTO dto) {
        final Dream dream = dreamRepository.getReferenceById(dto.getDreamId());

        if (!dream.isOwner(memberId)) {
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
