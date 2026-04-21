package com.mongault.kiku.dto;

import com.mongault.kiku.model.ReviewMode;
import lombok.Builder;
import java.time.LocalDate;

@Builder
public record CardReviewDto(
        Long cardId,
        ReviewMode mode,
        double easeFactor,
        int interval,
        LocalDate nextReview,
        int quality
) {}
