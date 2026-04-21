package com.mongault.kiku.repository;

import com.mongault.kiku.model.Card;
import com.mongault.kiku.model.ReviewMode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {



    List<Card> findByDeckId(Long deckId);

    // Find cards that need to be studied today in each category sorted by nextReview order
    @Query("""
        SELECT card FROM Card card
        JOIN card.reviews review
        WHERE card.deck.id = :deckId
        AND review.mode = :reviewMode 
        AND review.nextReview <= :today
        ORDER BY review.nextReview ASC
    """)
    List<Card> findDueCardsByMode(Long deckId, ReviewMode reviewMode, LocalDate today, Pageable pageable);


    // Find all cards that need to be studied today sorted by nextReview order
    @Query("""
        SELECT card FROM Card card
        JOIN card.reviews review
        WHERE card.deck.id = :deckId
        AND review.nextReview <= :today
        ORDER BY review.nextReview ASC
    """)
    List<Card> findDueCards(Long deckId, LocalDate today, Pageable pageable);
}
