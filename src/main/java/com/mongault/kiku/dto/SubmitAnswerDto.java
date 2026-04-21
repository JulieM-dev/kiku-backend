package com.mongault.kiku.dto;

import com.mongault.kiku.model.ReviewMode;

// What the Android client sends when submitting an answer
public record SubmitAnswerDto(
        ReviewMode mode,
        int quality
) {}