package org.example.kkumdoriland.member.ui;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.example.kkumdoriland.member.application.MemberService;
import org.example.kkumdoriland.member.dto.MemberJoinDTO;
import org.example.kkumdoriland.member.dto.MemberLoginDTO;
import org.example.kkumdoriland.member.dto.MemberResponse;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/login")
    public ResponseEntity<MemberResponse> login(@RequestBody MemberLoginDTO dto) {
        final MemberResponse user = userService.login(dto);

        return ResponseEntity.ok().body(user);
    }
}
