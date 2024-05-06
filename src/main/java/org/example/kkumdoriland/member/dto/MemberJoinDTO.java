package org.example.kkumdoriland.member.dto;


import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.kkumdoriland.member.domain.Member;
import org.example.kkumdoriland.member.domain.MemberRole;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberJoinDTO {
    private String name;
    private String email;
    private String password;

    public Member toMember(PasswordEncoder encoder) {
        return new Member(name, email, encoder.encode(password), MemberRole.USER);
    }
}
