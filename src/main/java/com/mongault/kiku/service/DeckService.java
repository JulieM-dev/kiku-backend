package com.mongault.kiku.service;

import com.mongault.kiku.model.Card;
import com.mongault.kiku.model.CardReview;
import com.mongault.kiku.model.Deck;
import com.mongault.kiku.model.ReviewMode;
import com.mongault.kiku.repository.DeckRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeckService {


    private final DeckRepository deckRepository;

    public Deck findById(Long id) {
        return deckRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Deck not found: " + id));
    }

    public List<Deck> findAll() {
        return deckRepository.findAll();
    }


    public Deck save(Deck deck) {
        if (deck == null) {
            throw new IllegalArgumentException("Deck is null");
        }
        return deckRepository.save(deck);
    }


    public Deck clearDeck(Deck deck) {
        if (deck == null) {
            throw new IllegalArgumentException("Deck is null");
        }
        deck.setCards(new ArrayList<Card>());
        return this.save(deck);
    }


    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        deckRepository.deleteById(id);
    }


}
