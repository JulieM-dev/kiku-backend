package com.mongault.kiku.service;

import com.mongault.kiku.factory.CardReviewFactory;
import com.mongault.kiku.model.Card;
import com.mongault.kiku.model.CardReview;
import com.mongault.kiku.model.Deck;
import com.mongault.kiku.model.ReviewMode;
import com.mongault.kiku.repository.CardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    //Limit of cards per day per deck (this is the maximum setting available in the application to not overwhelm the user)
    private static final int MAX_DUE_CARDS_PER_DECK = 60;
    //Limit of cards per mode per day per deck (this is the maximum setting available in the application to not overwhelm the user)
    private static final int MAX_DUE_CARDS_PER_MODE = 20;

    private final CardRepository cardRepository;
    private final CardReviewFactory cardReviewFactory;

    public Card findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        return cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card not found: " + id));
    }

    public List<Card> findByDeckId(Long deckId) {
        if (deckId == null) {
            throw new IllegalArgumentException("deckId is null");
        }
        return cardRepository.findByDeckId(deckId);
    }

    //get the cards that need to be trained today
    public List<Card> findDueCards(Long deckId) {
        if (deckId == null) {
            throw new IllegalArgumentException("deckId is null");
        }

        return cardRepository.findDueCards(
                deckId,
                LocalDate.now(),
                PageRequest.of(0, MAX_DUE_CARDS_PER_DECK)   //Limit of cards per deck per day
        );
    }

    //get the cards that need to be trained today
    public List<Card> findDueCardsByMode(Long deckId, ReviewMode mode) {
        if (deckId == null) {
            throw new IllegalArgumentException("deckId is null");
        }

        return cardRepository.findDueCardsByMode(
                deckId,
                mode,
                LocalDate.now(),
                PageRequest.of(0, MAX_DUE_CARDS_PER_MODE)   //Limit of cards per mode per day per deck
        );
    }

    public Card createCard(Card card) {
        card.getReviews().addAll(cardReviewFactory.createInitialReviews(card));
        return cardRepository.save(card);
    }

    public Card save(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("card is null");
        }
        return cardRepository.save(card);
    }


    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        cardRepository.deleteById(id);
    }

}
