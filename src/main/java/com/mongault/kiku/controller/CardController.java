package com.mongault.kiku.controller;

import com.mongault.kiku.dto.CardDto;
import com.mongault.kiku.dto.SubmitAnswerDto;
import com.mongault.kiku.mapper.CardMapper;
import com.mongault.kiku.model.Card;
import com.mongault.kiku.model.ReviewMode;
import com.mongault.kiku.service.CardService;
import com.mongault.kiku.service.DeckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;
    private final DeckService deckService;
    private final CardMapper cardMapper;

    @GetMapping("/deck/{deckId}")
    public List<CardDto> findByDeck(@PathVariable Long deckId) {
        return cardService.findByDeckId(deckId).stream()
                .map(cardMapper::toDto)
                .toList();
    }

    @GetMapping("/deck/{deckId}/due")
    public List<CardDto> findDue(@PathVariable Long deckId) {
        return cardService.findDueCards(deckId).stream()
                .map(cardMapper::toDto)
                .toList();
    }

    @GetMapping("/deck/{deckId}/duebymode")
    public List<CardDto> findDueByMode(@PathVariable Long deckId, ReviewMode mode) {
        return cardService.findDueCardsByMode(deckId, mode).stream()
                .map(cardMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public CardDto findById(@PathVariable Long id) {
        return cardMapper.toDto(cardService.findById(id));
    }

    @PostMapping("create/{deckId}")
    public ResponseEntity<CardDto> create(@PathVariable Long deckId, @RequestBody CardDto dto) {
        log.info("CardController info; received a card");
        log.debug("CardController debug; received a card");
        Card saved = cardService.createCard(
                cardMapper.newCardToEntity(dto, deckService.findById(deckId)));
        log.info("CardController info; card : " + saved.toString());
        return ResponseEntity.ok(cardMapper.toDto(saved));
    }

    @PostMapping("edit/{deckId}")
    public ResponseEntity<CardDto> edit(@PathVariable long deckId, @RequestBody CardDto dto) {
        log.info("CardController info; edit Card received");
        Card saved = cardService.save(
                cardMapper.toEntity(dto, deckService.findById(deckId)));
        log.info("CardController info; card edited : " + saved.toString());
        return ResponseEntity.ok(cardMapper.toDto(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cardService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}