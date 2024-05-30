package org.example.kkumdoriland.dream.ui;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.kkumdoriland.auth.dto.AuthContext;
import org.example.kkumdoriland.dream.dto.DailyHistoryCreateDTO;
import org.example.kkumdoriland.dream.dto.DailyHistoryResponse;
import org.example.kkumdoriland.dream.dto.DreamCreateDTO;
import org.example.kkumdoriland.dream.dto.DreamResponse;
import org.example.kkumdoriland.dream.dto.MileStoneCreateDTO;
import org.example.kkumdoriland.dream.dto.MileStoneResponse;
import org.example.kkumdoriland.dream.service.DailyHistoryService;
import org.example.kkumdoriland.dream.service.DreamService;
import org.example.kkumdoriland.dream.service.MileStoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dream")
@RequiredArgsConstructor
public class DreamController {
    public final DreamService dreamService;
    public final MileStoneService mileStoneService;
    public final DailyHistoryService dailyHistoryService;

    @GetMapping()
    public ResponseEntity<List<DreamResponse>> getDream(@AuthenticationPrincipal AuthContext authContext) {
        return ResponseEntity.ok(dreamService.getDream(authContext.getMemberDTO().getId()));
    }

    @PostMapping()
    public ResponseEntity<DreamResponse> createDream(@AuthenticationPrincipal AuthContext authContext, @Valid @RequestBody DreamCreateDTO dto) {
        final DreamResponse dream = dreamService.createDream(authContext.getMemberDTO().getId(), dto);

        return ResponseEntity.created(URI.create("/dream/" + dream.getId())).body(dream);
    }

    @PostMapping("/milestone")
    public ResponseEntity<MileStoneResponse> createMilestone(@AuthenticationPrincipal AuthContext authContext, @Valid @RequestBody MileStoneCreateDTO dto) {
        final MileStoneResponse mileStone = mileStoneService.createMilestone(authContext.getMemberDTO().getId(), dto);

        return ResponseEntity.created(URI.create("/dream/milestone" + mileStone.getId())).body(mileStone);
    }

    @PostMapping("/milestone/dailyHistory")
    public ResponseEntity<DailyHistoryResponse> createDailyHistory(@AuthenticationPrincipal AuthContext authContext, @Valid @RequestBody DailyHistoryCreateDTO dto) {
        final DailyHistoryResponse dailyHistory = dailyHistoryService.createDailyHistory(authContext.getMemberDTO().getId(), dto);

        return ResponseEntity.created(URI.create("/dream/milestone/dailyHistory" + dailyHistory.getId())).body(dailyHistory);
    }
}
