package org.example.kkumdoriland.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.kkumdoriland.member.domain.Member;

@Getter
@AllArgsConstructor
public class MemberResponse {
    private final Long id;
    private final String name;

    public static MemberResponse of(Member user) {
        return new MemberResponse(user.getId(), user.getName());
    }
}
