package com.mongault.kiku.service;

import com.mongault.kiku.model.Card;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class AudioService {


    @Value("${app.voicevox.base-url}")
    private String voicevoxBaseUrl;

    private final CardService cardService;

    private final WebClient webClient = WebClient.builder()
            .codecs(configurer -> configurer
                    .defaultCodecs()
                    .maxInMemorySize(10 * 1024 * 1024))
            .build();

    public void streamAudio(String japaneseText, OutputStream outputStream) throws IOException {
        Random random = new Random();
        int min = 5;
        int max = 8;
        int speaker = random.nextInt((max - min) + 1) + min;


        // Step 1 — get audio query from VoiceVox
        String audioQuery = webClient.post()
                .uri(voicevoxBaseUrl + "/audio_query?text={text}&speaker=1000" + speaker,
                        japaneseText)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Step 2 — synthesize and stream bytes directly to client
        byte[] audioBytes = webClient.post()
                .uri(voicevoxBaseUrl + "/synthesis?speaker=1000"+speaker)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .bodyValue(audioQuery)
                .retrieve()
                .bodyToMono(byte[].class)
                .block();

        outputStream.write(audioBytes);
    }

    public void streamAudioByCardId (Long cardId, OutputStream outputStream) throws IOException {
        Card card = cardService.findById(cardId);
        streamAudio(card.getJapanese(), outputStream);
    }
}