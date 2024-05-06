package org.example.kkumdoriland.member.ui;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.example.kkumdoriland.member.service.MemberService;
import org.example.kkumdoriland.member.dto.MemberJoinDTO;
import org.example.kkumdoriland.member.dto.MemberResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {
    public final MemberService userService;

    @PostMapping("/join")
    public ResponseEntity<MemberResponse> join(@RequestBody MemberJoinDTO dto) {
        final MemberResponse user = userService.join(dto);

        return ResponseEntity.created(URI.create("/user/" + user.getId())).body(user);
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest httpServletRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            new SecurityContextLogoutHandler().logout(httpServletRequest, null, authentication);
        }

        return ResponseEntity.noContent().build();
    }
}
