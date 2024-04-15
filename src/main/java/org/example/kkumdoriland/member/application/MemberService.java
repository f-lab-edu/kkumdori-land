package org.example.kkumdoriland.member.application;

import lombok.RequiredArgsConstructor;
import org.example.kkumdoriland.member.domain.Member;
import org.example.kkumdoriland.member.dto.MemberJoinDTO;
import org.example.kkumdoriland.member.dto.MemberLoginDTO;
import org.example.kkumdoriland.member.dto.MemberResponse;
import org.example.kkumdoriland.member.exception.MemberErrorCode;
import org.example.kkumdoriland.member.exception.MemberException;
import org.example.kkumdoriland.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberResponse join(MemberJoinDTO dto) {
        final Member userToCreate = toUser(dto.getName(), dto.getEmail(), dto.getPassword());

        // validation logic
        validateDuplicatedEmail(userToCreate);

        return MemberResponse.of(userRepository.save(userToCreate));
    }

    public MemberResponse login(MemberLoginDTO dto) {
        final Member user = userRepository.findUserByEmail(dto.getEmail())
                .orElseThrow(() -> new MemberException(MemberErrorCode.USER_NOT_FOUND, "사용자를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new MemberException(MemberErrorCode.USER_PASSWORD_MISMATCH, "비밀번호가 일치하지 않습니다.");
        }

        return MemberResponse.of(user);
    }

    private void validateDuplicatedEmail(Member user) {
        if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            throw new MemberException(MemberErrorCode.USER_EMAIL_DUPLICATION, "이미 존재하는 이메일입니다.");
        }
    }

    private Member toUser(String name, String email, String password) {
        return new Member(name, email, passwordEncoder.encode(password));
    }

}
