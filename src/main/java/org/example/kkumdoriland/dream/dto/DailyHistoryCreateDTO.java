package org.example.kkumdoriland.dream.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.kkumdoriland.dream.domain.DailyHistory;
import org.example.kkumdoriland.dream.domain.MileStone;
import org.jetbrains.annotations.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DailyHistoryCreateDTO {
    @NotNull
    private String contentText;

    @NotNull
    private Integer stepSize;

    @NotNull
    private Long mileStoneId;

    public DailyHistory toDailyHistory(MileStone mileStone) {
        return new DailyHistory(contentText, stepSize, mileStone);
    }
}
