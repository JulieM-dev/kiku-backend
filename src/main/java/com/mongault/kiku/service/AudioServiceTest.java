package com.mongault.kiku.service;

import com.mongault.kiku.model.Card;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class AudioServiceTest {

    private final CardService cardService;

    public void streamAudio(Long cardId, OutputStream outputStream) throws IOException {
        // TODO: replace with VoiceVox generation
        ClassPathResource audio = new ClassPathResource("audio/audiotest.wav");
        outputStream.write(audio.getContentAsByteArray());
    }
}