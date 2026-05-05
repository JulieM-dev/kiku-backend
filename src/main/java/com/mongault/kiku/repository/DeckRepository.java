package com.mongault.kiku.repository;


import com.mongault.kiku.model.CardReview;
import com.mongault.kiku.model.Deck;
import com.mongault.kiku.model.ReviewMode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeckRepository extends JpaRepository<Deck, Long> {

    List<Deck> findByUserId(Long userId);

    Optional<Deck> findByIdAndUserId(Long deckId, Long userId);

}
