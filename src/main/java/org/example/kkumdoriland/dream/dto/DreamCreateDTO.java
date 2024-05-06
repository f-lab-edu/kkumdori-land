package org.example.kkumdoriland.dream.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@RequiredArgsConstructor
public class DreamCreateDTO {
    @NotNull
    private final String title;

    @NotNull
    private final String description;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final String dueDate;
}
