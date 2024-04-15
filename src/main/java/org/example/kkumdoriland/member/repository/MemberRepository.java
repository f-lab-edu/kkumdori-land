package org.example.kkumdoriland.member.repository;

import java.util.Optional;
import org.example.kkumdoriland.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findUserByEmail(String email);
}
