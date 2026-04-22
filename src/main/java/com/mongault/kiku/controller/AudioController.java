package com.mongault.kiku.controller;

import com.mongault.kiku.service.AudioService;
import com.mongault.kiku.service.AudioServiceTest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequestMapping("/api/audio")
@RequiredArgsConstructor
public class AudioController {

    private final AudioService audioService;

    @GetMapping(value = "/card/{cardId}", produces = "audio/wav")
    public ResponseEntity<StreamingResponseBody> streamAudio(@PathVariable Long cardId) {
        StreamingResponseBody stream = outputStream ->
                audioService.streamAudioByCardId(cardId, outputStream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "audio/wav")
                .header(HttpHeaders.ACCEPT_RANGES, "bytes")
                .header(HttpHeaders.CACHE_CONTROL, "public, max-age=86400")
                .body(stream);
    }

    @GetMapping(value = "/text/{japaneseText}", produces = "audio/wav")
    public ResponseEntity<StreamingResponseBody> streamAudio(@PathVariable String japaneseText) {
        StreamingResponseBody stream = outputStream ->
                audioService.streamAudio(japaneseText, outputStream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "audio/wav")
                .header(HttpHeaders.ACCEPT_RANGES, "bytes")
                .header(HttpHeaders.CACHE_CONTROL, "public, max-age=86400")
                .body(stream);
    }
}