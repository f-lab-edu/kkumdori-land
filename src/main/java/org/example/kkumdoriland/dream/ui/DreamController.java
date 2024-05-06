package org.example.kkumdoriland.dream.ui;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.example.kkumdoriland.dream.dto.DreamCreateDTO;
import org.example.kkumdoriland.dream.dto.DreamResponse;
import org.example.kkumdoriland.dream.service.DreamService;
import org.example.kkumdoriland.member.dto.MemberDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dream")
@RequiredArgsConstructor
public class DreamController {
    public final DreamService dreamService;

    @PostMapping()
    public ResponseEntity<DreamResponse> createDream(@AuthenticationPrincipal MemberDTO member, @RequestBody DreamCreateDTO dto) {
        final DreamResponse dream = dreamService.createDream(member.getId(), dto);

        return ResponseEntity.created(URI.create("/dream/" + dream.getId())).body(dream);
    }


}
