package org.example.kkumdoriland.dream.service;

import lombok.RequiredArgsConstructor;
import org.example.kkumdoriland.dream.domain.DailyHistory;
import org.example.kkumdoriland.dream.domain.MileStone;
import org.example.kkumdoriland.dream.dto.DailyHistoryCreateDTO;
import org.example.kkumdoriland.dream.dto.DailyHistoryResponse;
import org.example.kkumdoriland.dream.repository.DailyHistoryRepository;
import org.example.kkumdoriland.dream.repository.MileStoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DailyHistoryService {
    private final MileStoneRepository mileStoneRepository;
    private final DailyHistoryRepository dailyHistoryRepository;

    @Transactional
    public DailyHistoryResponse createDailyHistory(Long memberId, DailyHistoryCreateDTO dto) {
        final MileStone mileStone = mileStoneRepository.getReferenceById(dto.getMileStoneId());

        if (!mileStone.isOwner(memberId)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }

        final DailyHistory dailyHistory = dto.toDailyHistory(mileStone);
        dailyHistoryRepository.save(dailyHistory);

        return DailyHistoryResponse.of(dailyHistory);
    }
}
