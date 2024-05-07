package org.example.kkumdoriland.dream.ui;

import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.example.kkumdoriland.auth.dto.AuthContext;
import org.example.kkumdoriland.dream.dto.DailyHistoryCreateDTO;
import org.example.kkumdoriland.dream.dto.DailyHistoryResponse;
import org.example.kkumdoriland.dream.dto.DreamCreateDTO;
import org.example.kkumdoriland.dream.dto.DreamResponse;
import org.example.kkumdoriland.dream.dto.MileStoneCreateDTO;
import org.example.kkumdoriland.dream.dto.MileStoneResponse;
import org.example.kkumdoriland.dream.service.DreamService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dream")
@RequiredArgsConstructor
public class DreamController {
    public final DreamService dreamService;

    @GetMapping("/{dreamId}")
    public ResponseEntity<DreamResponse> getDream(@PathVariable Long dreamId) {
        final DreamResponse dream = dreamService.getDream(dreamId);

        return ResponseEntity.ok(dream);
    }

    @PostMapping()
    public ResponseEntity<DreamResponse> createDream(@AuthenticationPrincipal AuthContext authContext, @Valid @RequestBody DreamCreateDTO dto) {
        final DreamResponse dream = dreamService.createDream(authContext.getMemberDTO().getId(), dto);

        return ResponseEntity.created(URI.create("/dream/" + dream.getId())).body(dream);
    }

    @PostMapping("/milestone")
    public ResponseEntity<MileStoneResponse> createMilestone(@AuthenticationPrincipal AuthContext authContext, @Valid @RequestBody MileStoneCreateDTO dto) {
        final MileStoneResponse mileStone = dreamService.createMilestone(authContext.getMemberDTO().getId(), dto);

        return ResponseEntity.created(URI.create("/dream/milestone" + mileStone.getId())).body(mileStone);
    }

    @PostMapping("/milestone/dailyHistory")
    public ResponseEntity<DailyHistoryResponse> createDailyHistory(@AuthenticationPrincipal AuthContext authContext, @Valid @RequestBody DailyHistoryCreateDTO dto) {
        final DailyHistoryResponse dailyHistory = dreamService.createDailyHistory(authContext.getMemberDTO().getId(), dto);

        return ResponseEntity.created(URI.create("/dream/milestone/dailyHistory" + dailyHistory.getId())).body(dailyHistory);
    }
}
