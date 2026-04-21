package com.mongault.kiku.dto;

import com.mongault.kiku.model.FormalityLevel;
import lombok.Builder;

@Builder
public record CardDto(
        Long id,
        String japanese,
        String kana,
        String romaji,
        String translation,
        FormalityLevel formalityLevel,
        Long deckId
) {}