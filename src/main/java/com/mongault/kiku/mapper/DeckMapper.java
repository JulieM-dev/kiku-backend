package com.mongault.kiku.mapper;

import com.mongault.kiku.dto.DeckDto;
import com.mongault.kiku.model.Deck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeckMapper {

    private final CardMapper cardMapper;

    public DeckDto toDto(Deck deck) {
        return DeckDto.builder()
                .id(deck.getId())
                .name(deck.getName())
                .description(deck.getDescription())
                .cards(deck.getCards().stream()
                        .map(cardMapper::toDto)
                        .toList())
                .build();
    }

    public Deck toEntity(DeckDto dto) {
        return Deck.builder()
                .id(dto.id())
                .name(dto.name())
                .description(dto.description())
                .build();
    }
}
