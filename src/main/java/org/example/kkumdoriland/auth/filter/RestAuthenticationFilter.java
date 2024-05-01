package org.example.kkumdoriland.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.example.kkumdoriland.auth.token.RestAuthenticationToken;
import org.example.kkumdoriland.member.dto.MemberDTO;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class RestAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RestAuthenticationFilter(HttpSecurity http, String defaultFilterProcessUrl, String processMethod) {
        super(new AntPathRequestMatcher(defaultFilterProcessUrl, processMethod));

        setSecurityContextRepository(getSecurityContextRepository(http));
    }

    private SecurityContextRepository getSecurityContextRepository(HttpSecurity http) {
        SecurityContextRepository securityContextRepository = http.getSharedObject(SecurityContextRepository.class);
        if(securityContextRepository == null) {
            securityContextRepository = new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
            );
        }

        return securityContextRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        if(!HttpMethod.POST.name().equals(request.getMethod())) {
            throw new IllegalArgumentException("인증이 불가한 메소드 입니다.");
        }

        final MemberDTO memberDTO = objectMapper.readValue(request.getReader(), MemberDTO.class);
        if(StringUtils.isBlank(memberDTO.getEmail()) || StringUtils.isBlank(memberDTO.getPassword()))  {
            throw new AuthenticationServiceException("email과 password는 꼭 입력 되어야 합니다.");
        }

        final RestAuthenticationToken token = new RestAuthenticationToken(
            memberDTO.getEmail(),
            memberDTO.getPassword()
        );

        return getAuthenticationManager().authenticate(token);
    }
}
