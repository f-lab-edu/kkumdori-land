package org.example.kkumdoriland.dream.dto;

import lombok.Getter;
import org.example.kkumdoriland.dream.domain.MileStone;

@Getter
public class MileStoneResponse {
    private Long id;
    private String title;
    private String description;
    private boolean achieved;
    private int totalScoreToEarn;
    private int minimumStepSize;

    public static MileStoneResponse of(MileStone mileStone) {
        MileStoneResponse response = new MileStoneResponse();

        response.id = mileStone.getId();
        response.title = mileStone.getTitle();
        response.description = mileStone.getDescription();
        response.achieved = mileStone.isAchieved();
        response.totalScoreToEarn = mileStone.getTotalScoreToEarn();
        response.minimumStepSize = mileStone.getMinimumStepSize();

        return response;
    }
}
