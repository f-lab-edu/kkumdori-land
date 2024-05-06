package org.example.kkumdoriland.dream.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import org.example.kkumdoriland.dream.domain.Dream;

@Getter
public class DreamResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private Long ownerId;

    public static DreamResponse of(Dream savedDream) {
        final DreamResponse response = new DreamResponse();

        response.id = savedDream.getId();
        response.title = savedDream.getTitle();
        response.description = savedDream.getDescription();
        response.dueDate = savedDream.getDueDate();
        response.ownerId = savedDream.getUser().getId();

        return response;
    }
}
