package com.mongault.kiku.service;

import com.mongault.kiku.factory.CardReviewFactory;
import com.mongault.kiku.model.Card;
import com.mongault.kiku.model.CardReview;
import com.mongault.kiku.model.ReviewMode;
import com.mongault.kiku.repository.CardReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardReviewService {

    private final CardReviewRepository cardReviewRepository;
    private final CardReviewFactory cardReviewFactory;
    private final CardService cardService;

    public CardReview initReview(Long cardId, ReviewMode mode) {
        Card card = cardService.findById(cardId);
        CardReview review = cardReviewFactory.create(card, mode);
        return cardReviewRepository.save(review);
    }


    @Transactional
    public CardReview submitAnswer(Long cardId, ReviewMode mode, int quality) {
        CardReview review = cardReviewRepository.findByCardIdAndMode(cardId, mode)
                .orElseThrow(() -> new EntityNotFoundException("Review not found for card: " + cardId + " and mode: " + mode));

        applyS2(review, quality);
        review.setReviewedAt(LocalDate.now());
        return cardReviewRepository.save(review);
    }


    // SM-2 algorithm
    private void applyS2(CardReview review, int quality) {
        if (quality < 0 || quality > 5) {
            throw new IllegalArgumentException("Quality must be between 0 and 5");
        }

        if (quality >= 3) {
            // Correct answer — grow the interval
            int newInterval = (int) Math.round(review.getInterval() * review.getEaseFactor());
            review.setInterval(Math.max(newInterval, review.getInterval() + 1));
        } else {
            // Wrong answer — reset
            review.setInterval(1);
        }

        // Adjust ease factor
        double newEaseFactor = review.getEaseFactor()
                + 0.1 - (5 - quality) * 0.08;
        review.setEaseFactor(Math.max(1.3, newEaseFactor));

        review.setQuality(quality);
        review.setNextReview(LocalDate.now().plusDays(review.getInterval()));
    }



    public List<CardReview> findByCardId(Long cardId) {
        if (cardId == null) {
            throw new IllegalArgumentException("cardId is null");
        }
        return cardReviewRepository.findByCardId(cardId);
    }

    public CardReview findByCardIdAndMode(Long cardId, ReviewMode mode) {
        if (cardId == null) {
            throw new IllegalArgumentException("deckId is null");
        }
        if (mode == null) {
            throw new IllegalArgumentException("mode is null");
        }
        CardReview review = cardReviewRepository.findByCardIdAndMode(cardId, mode)
                .orElseThrow(() -> new EntityNotFoundException("Review not found for card: " + cardId + " and mode: " + mode));
        return review;
    }


    public CardReview save(CardReview review) {
        if (review == null) {
            throw new IllegalArgumentException("CardReview is null");
        }
        return cardReviewRepository.save(review);
    }

    public List<CardReview> save(List<CardReview> reviews) {
        if (reviews == null) {
            throw new IllegalArgumentException("List<CardReview> is null");
        }
        return cardReviewRepository.saveAll(reviews);
    }



    public CardReview reset(CardReview review) {
        if (review == null) {
            throw new IllegalArgumentException("review is null");
        }
        if (review.getCard() == null) {
            throw new IllegalArgumentException("review.card is null");
        }
        return save(cardReviewFactory.create(review.getCard(), review.getMode()));
    }

    public List<CardReview> resetAll(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("card is null");
        }
        return save(cardReviewFactory.createInitialReviews(card));
    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        cardReviewRepository.deleteById(id);
    }





}
