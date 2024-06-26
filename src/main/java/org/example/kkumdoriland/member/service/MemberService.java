package org.example.kkumdoriland.member.service;

import lombok.RequiredArgsConstructor;
import org.example.kkumdoriland.member.domain.Member;
import org.example.kkumdoriland.member.dto.MemberJoinDTO;
import org.example.kkumdoriland.member.dto.MemberResponse;
import org.example.kkumdoriland.member.exception.MemberErrorCode;
import org.example.kkumdoriland.member.exception.MemberException;
import org.example.kkumdoriland.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberResponse join(MemberJoinDTO dto) {
        final Member userToCreate = dto.toMember(passwordEncoder);

        // validation logic
        validateDuplicatedEmail(userToCreate);

        return MemberResponse.of(memberRepository.save(userToCreate));
    }

    private void validateDuplicatedEmail(Member user) {
        if (memberRepository.findMemberByEmail(user.getEmail()).isPresent()) {
            throw new MemberException(MemberErrorCode.USER_EMAIL_DUPLICATION, "이미 존재하는 이메일입니다.");
        }
    }

}
