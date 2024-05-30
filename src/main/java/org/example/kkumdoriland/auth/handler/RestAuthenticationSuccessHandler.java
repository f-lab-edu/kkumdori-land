package org.example.kkumdoriland.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.example.kkumdoriland.auth.dto.AuthContext;
import org.example.kkumdoriland.member.dto.MemberResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,  Authentication authentication) throws IOException, ServletException {
        final ObjectMapper mapper = new ObjectMapper();

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        final AuthContext authContext = (AuthContext) authentication.getPrincipal();
        final MemberResponse memberResponse = new MemberResponse(authContext.getMemberDTO().getId(), authContext.getMemberDTO().getName());

        mapper.writeValue(response.getWriter(), memberResponse);
    }
}
