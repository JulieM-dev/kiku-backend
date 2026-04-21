package com.mongault.kiku.factory;

import com.mongault.kiku.model.Card;
import com.mongault.kiku.model.CardReview;
import com.mongault.kiku.model.ReviewMode;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class CardReviewFactory {

    public CardReview create(Card card, ReviewMode mode) {
        return CardReview.builder()
                .card(card)
                .nextReview(LocalDate.now())
                .mode(mode)
                .build();
    }

    //one review per mode so each card have 3 independents exercices
    public List<CardReview> createInitialReviews(Card card) {
        return List.of(
                create(card, ReviewMode.COMPREHENSION),
                create(card, ReviewMode.EXPRESSION),
                create(card, ReviewMode.PRONUNCIATION)
        );
    }
}