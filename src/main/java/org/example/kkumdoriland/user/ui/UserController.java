package org.example.kkumdoriland.user.ui;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.example.kkumdoriland.user.application.UserService;
import org.example.kkumdoriland.user.dto.UserJoinDTO;
import org.example.kkumdoriland.user.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    public final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<UserResponse> join(@RequestBody UserJoinDTO dto) {
        UserResponse user = userService.join(dto);
        return ResponseEntity.created(URI.create("/user/" + user.getId())).body(user);
    }
}
