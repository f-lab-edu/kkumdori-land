package org.example.kkumdoriland.auth.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.kkumdoriland.member.domain.Member;
import org.example.kkumdoriland.auth.dto.AuthContext;
import org.example.kkumdoriland.member.dto.MemberDTO;
import org.example.kkumdoriland.member.repository.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<Member> result = memberRepository.findMemberByEmail(username);

        if(result.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        final Member member = result.get();
        final List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(member.getRoles().name()));

        final MemberDTO memberDTO = MemberDTO.builder()
            .id(member.getId())
            .email(member.getEmail())
            .password(member.getPassword())
            .name(member.getName())
            .roles(member.getRoles())
            .build();

        return new AuthContext(memberDTO, authorities);
    }
}
