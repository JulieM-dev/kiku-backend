package com.mongault.kiku.controller;

import com.mongault.kiku.dto.DeckDto;
import com.mongault.kiku.mapper.DeckMapper;
import com.mongault.kiku.model.Deck;
import com.mongault.kiku.service.DeckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/decks")
@RequiredArgsConstructor
public class DeckController {

    private final DeckService deckService;
    private final DeckMapper deckMapper;

    @GetMapping
    public List<DeckDto> findAll() {
        return deckService.findAll().stream()
                .map(deckMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public DeckDto findById(@PathVariable Long id) {
        return deckMapper.toDto(deckService.findById(id));
    }

    @PostMapping
    public ResponseEntity<DeckDto> create(@RequestBody DeckDto dto) {
        Deck saved = deckService.save(deckMapper.toEntity(dto));
        return ResponseEntity.ok(deckMapper.toDto(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deckService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}