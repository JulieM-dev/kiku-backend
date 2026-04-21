package com.mongault.kiku.controller;

import com.mongault.kiku.dto.CardReviewDto;
import com.mongault.kiku.dto.SubmitAnswerDto;
import com.mongault.kiku.mapper.CardReviewMapper;
import com.mongault.kiku.model.CardReview;
import com.mongault.kiku.model.ReviewMode;
import com.mongault.kiku.service.CardReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class CardReviewController {

    private final CardReviewService reviewService;
    private final CardReviewMapper cardReviewMapper;

    @PostMapping("/card/{cardId}/init")
    public ResponseEntity<CardReviewDto> init(@PathVariable Long cardId,
                                              @RequestParam ReviewMode mode) {
        CardReview review = reviewService.initReview(cardId, mode);
        return ResponseEntity.ok(cardReviewMapper.toDto(review));
    }

    @PostMapping("/card/{cardId}/answer")
    public ResponseEntity<CardReviewDto> submitAnswer(@PathVariable Long cardId,
                                                      @RequestBody SubmitAnswerDto dto) {
        CardReview review = reviewService.submitAnswer(cardId, dto.mode(), dto.quality());
        return ResponseEntity.ok(cardReviewMapper.toDto(review));
    }


}