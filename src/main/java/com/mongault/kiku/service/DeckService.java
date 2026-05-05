package com.mongault.kiku.service;

import com.mongault.kiku.factory.DeckFactory;
import com.mongault.kiku.model.*;
import com.mongault.kiku.repository.DeckRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeckService {


    private final DeckRepository deckRepository;
    private final DeckFactory deckFactory;

    public Deck findById(Long id, Long userId) {
        return deckRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new EntityNotFoundException("Deck not found: " + id));
    }

    public List<Deck> findAll(Long userId) {
        return deckRepository.findByUserId(userId);
    }


    public Deck save(Deck deck) {
        if (deck == null) {
            throw new IllegalArgumentException("Deck is null");
        }
        return deckRepository.save(deck);
    }

    @Transactional
    public void createDefaultDecks(User user) {
        deckFactory.createDefaultDecks(user);
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
