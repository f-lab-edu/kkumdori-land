package org.example.kkumdoriland.member.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.kkumdoriland.common.domain.BaseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<MemberRole> roles = new HashSet<>();

    public Member(PasswordEncoder passwordEncoder, String name, String email, String password, Set<MemberRole> roles) {
        this.name = name;
        this.email = email;
        this.password = passwordEncoder.encode(password);
        this.roles = Set.copyOf(roles);
    }
}
