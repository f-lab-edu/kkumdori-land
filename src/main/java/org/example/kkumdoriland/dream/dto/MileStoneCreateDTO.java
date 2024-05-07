package org.example.kkumdoriland.dream.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MileStoneCreateDTO {
    private Long dreamId;
    @NotNull
    private String title;
    @NotNull
    private String description;
    private int totalScoreToEarn;
    private int minimumStepSize;
}
