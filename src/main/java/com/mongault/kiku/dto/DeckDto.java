package com.mongault.kiku.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record DeckDto(
        Long id,
        String name,
        String description,
        List<CardDto> cards
) {}