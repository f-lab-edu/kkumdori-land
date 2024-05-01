package org.example.kkumdoriland.auth.dto;

import java.util.Collection;
import java.util.List;
import lombok.Data;
import org.example.kkumdoriland.member.dto.MemberDTO;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class AuthContext implements UserDetails {
    private MemberDTO memberDTO;
    private final List<GrantedAuthority> roles;

    public AuthContext(MemberDTO memberDTO, List<GrantedAuthority> roles) {
        this.memberDTO = memberDTO;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Nullable
    @Override
    public String getPassword() {
        return memberDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return memberDTO.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
