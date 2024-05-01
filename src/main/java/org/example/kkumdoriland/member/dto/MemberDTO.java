package org.example.kkumdoriland.member.dto;


import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.kkumdoriland.member.domain.MemberRole;
import org.jetbrains.annotations.Nullable;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Long id;
    private String email;
    private String name;

    @Nullable
    private String password;
    private MemberRole roles;

    public void clearPassword() {
        password = null;
    }
}
