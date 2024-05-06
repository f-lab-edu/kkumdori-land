package org.example.kkumdoriland.dream.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.example.kkumdoriland.dream.domain.Dream;
import org.example.kkumdoriland.dream.dto.DreamCreateDTO;
import org.example.kkumdoriland.dream.dto.DreamResponse;
import org.example.kkumdoriland.dream.repository.DreamRepository;
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

    @Transactional
    public DreamResponse createDream(Long memberId, DreamCreateDTO dto) {
        final Member member = memberRepository.findMemberById(memberId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        final Dream dream = Dream.builder()
            .user(member)
            .title(dto.getTitle())
            .description(dto.getDescription())
            .dueDate(LocalDateTime.parse(dto.getDueDate()))
            .build();

        final Dream savedDream = dreamRepository.save(dream);

        return DreamResponse.of(savedDream);
    }
}
