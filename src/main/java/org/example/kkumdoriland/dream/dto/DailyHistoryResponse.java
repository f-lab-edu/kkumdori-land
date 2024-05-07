package org.example.kkumdoriland.dream.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import org.example.kkumdoriland.dream.domain.DailyHistory;

@Getter
public class DailyHistoryResponse {

    private Long id;

    private String contentText;
    private Integer stepSize;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static DailyHistoryResponse of(DailyHistory dailyHistory) {
        final DailyHistoryResponse response = new DailyHistoryResponse();

        response.id = dailyHistory.getId();
        response.contentText = dailyHistory.getContentText();
        response.stepSize = dailyHistory.getStepSize();
        response.createdAt = dailyHistory.getCreatedAt();
        response.updatedAt = dailyHistory.getUpdatedAt();

        return response;
    }
}
