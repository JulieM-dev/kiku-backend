package com.mongault.kiku.mapper;

import com.mongault.kiku.dto.CardReviewDto;
import com.mongault.kiku.model.CardReview;
import org.springframework.stereotype.Component;

@Component
public class CardReviewMapper {

    public CardReviewDto toDto(CardReview review) {
        return CardReviewDto.builder()
                .cardId(review.getCard().getId())
                .mode(review.getMode())
                .easeFactor(review.getEaseFactor())
                .interval(review.getInterval())
                .nextReview(review.getNextReview())
                .quality(review.getQuality())
                .build();
    }
}