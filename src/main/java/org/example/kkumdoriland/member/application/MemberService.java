package org.example.kkumdoriland.member.application;

import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.kkumdoriland.member.domain.Member;
import org.example.kkumdoriland.member.dto.AuthMemberDTO;
import org.example.kkumdoriland.member.dto.MemberJoinDTO;
import org.example.kkumdoriland.member.dto.MemberLoginDTO;
import org.example.kkumdoriland.member.dto.MemberResponse;
import org.example.kkumdoriland.member.exception.MemberErrorCode;
import org.example.kkumdoriland.member.exception.MemberException;
import org.example.kkumdoriland.member.repository.MemberRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberResponse join(MemberJoinDTO dto) {
        final Member userToCreate = dto.toMember(passwordEncoder);

        // validation logic
        validateDuplicatedEmail(userToCreate);

        return MemberResponse.of(memberRepository.save(userToCreate));
    }

    public MemberResponse login(MemberLoginDTO dto) {
        final Member user = memberRepository.findMemberByEmail(dto.getEmail())
                .orElseThrow(() -> new MemberException(MemberErrorCode.USER_NOT_FOUND, "사용자를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new MemberException(MemberErrorCode.USER_PASSWORD_MISMATCH, "비밀번호가 일치하지 않습니다.");
        }

        return MemberResponse.of(user);
    }

    private void validateDuplicatedEmail(Member user) {
        if (memberRepository.findMemberByEmail(user.getEmail()).isPresent()) {
            throw new MemberException(MemberErrorCode.USER_EMAIL_DUPLICATION, "이미 존재하는 이메일입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> result = memberRepository.findMemberByEmail(username);

        if(result.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        Member member = result.get();

        AuthMemberDTO authMember = new AuthMemberDTO(
            member.getName(),
            member.getEmail(),
            member.getPassword(),
            member.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toSet())
        );

        return authMember;
    }
}
