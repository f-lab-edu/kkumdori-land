package org.example.kkumdoriland.dream.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Getter
@RequiredArgsConstructor
public class DreamCreateDTO {
    @NotNull
    private final String title;
    @NotNull
    private final String description;
    @NotNull
    private final String dueDate;
}
