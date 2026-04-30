package com.mongault.kiku.mapper;

import com.mongault.kiku.dto.CardDto;
import com.mongault.kiku.model.Card;
import com.mongault.kiku.model.Deck;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public CardDto toDto(Card card) {
        return CardDto.builder()
                .id(card.getId())
                .initialOrder(card.getInitialOrder())
                .japanese(card.getJapanese())
                .kana(card.getKana())
                .romaji(card.getRomaji())
                .translation(card.getTranslation())
                .formalityLevel(card.getFormalityLevel())
                .deckId(card.getDeck().getId())
                .build();
    }

    public Card toEntity(CardDto dto, Deck deck) {
        return Card.builder()
                .id(dto.id())
                .initialOrder(dto.initialOrder())
                .japanese(dto.japanese())
                .kana(dto.kana())
                .romaji(dto.romaji())
                .translation(dto.translation())
                .formalityLevel(dto.formalityLevel())
                .deck(deck)
                .build();
    }

    public Card newCardToEntity(CardDto dto, Deck deck) {
        return Card.builder()
                .japanese(dto.japanese())
                .kana(dto.kana())
                .romaji(dto.romaji())
                .translation(dto.translation())
                .formalityLevel(dto.formalityLevel())
                .deck(deck)
                .build();
    }
}