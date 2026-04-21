package com.mongault.kiku.repository;

import com.mongault.kiku.model.CardReview;
import com.mongault.kiku.model.ReviewMode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardReviewRepository extends JpaRepository<CardReview, Long> {

    List<CardReview> findByCardId(Long cardId);

    Optional<CardReview> findByCardIdAndMode(Long cardId, ReviewMode mode);
}
