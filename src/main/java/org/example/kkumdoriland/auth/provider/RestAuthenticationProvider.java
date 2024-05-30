package org.example.kkumdoriland.auth.provider;

import lombok.RequiredArgsConstructor;
import org.example.kkumdoriland.auth.token.RestAuthenticationToken;
import org.example.kkumdoriland.auth.dto.AuthContext;
import org.example.kkumdoriland.member.dto.MemberDTO;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication)
        throws AuthenticationException {

        final String email = authentication.getName();
        final String password = (String) authentication.getCredentials();

        AuthContext authContext = (AuthContext) userDetailsService.loadUserByUsername(email);

        if (!passwordEncoder.matches(password, authContext.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        final MemberDTO memberDTO = authContext.getMemberDTO();
        memberDTO.clearPassword();

        return new RestAuthenticationToken(authContext, null, authContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(RestAuthenticationToken.class);
    }
}
