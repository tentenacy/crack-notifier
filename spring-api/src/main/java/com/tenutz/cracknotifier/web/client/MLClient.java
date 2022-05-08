package com.tenutz.cracknotifier.web.client;

import com.tenutz.cracknotifier.util.file.FileSystemResource;
import com.tenutz.cracknotifier.web.client.dto.ml.PredictionMLResponse;
import com.tenutz.cracknotifier.web.api.exception.io.CIOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class MLClient {

    private final WebClient webClient;

    @Value("${ext.ml.host}")
    private String hostUrl;

    public PredictionMLResponse predict(MultipartFile image) throws IOException {
        return webClient.post()
                .uri(hostUrl+"/predictions")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData("file", new FileSystemResource(image.getBytes(), image.getOriginalFilename())))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, mlResponse -> {
                    log.debug("mlResponse = " + mlResponse);
                    return Mono.error(new CIOException.CMLCommunicationException());
                })
                .onStatus(HttpStatus::is5xxServerError, mlResponse -> {
                    log.debug("mlResponse = " + mlResponse);
                    return Mono.error(new CIOException.CMLCommunicationException());
                })
                .bodyToMono(PredictionMLResponse.class)
                .block();
    }
}
