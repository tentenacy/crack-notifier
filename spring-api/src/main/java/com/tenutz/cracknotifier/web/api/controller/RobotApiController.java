package com.tenutz.cracknotifier.web.api.controller;

import com.tenutz.cracknotifier.web.api.dto.crack.CrackCreateRequest;
import com.tenutz.cracknotifier.web.api.dto.crack.CrackDetectRequest;
import com.tenutz.cracknotifier.web.api.dto.robot.RobotCreateRequest;
import com.tenutz.cracknotifier.web.api.dto.robot.RobotCreateResponse;
import com.tenutz.cracknotifier.web.api.dto.robot.RobotDetailsResponse;
import com.tenutz.cracknotifier.web.api.dto.user.UserDetailsResponse;
import com.tenutz.cracknotifier.web.api.exception.io.CIOException;
import com.tenutz.cracknotifier.web.api.service.CrackService;
import com.tenutz.cracknotifier.web.api.service.RobotService;
import com.tenutz.cracknotifier.web.api.service.cloud.FileUploadService;
import com.tenutz.cracknotifier.web.client.MLClient;
import com.tenutz.cracknotifier.web.client.dto.ml.PredictionMLResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/robots")
@RequiredArgsConstructor
public class RobotApiController {

    private final RobotService robotService;
    private final CrackService crackService;
    private final MLClient mlClient;
    private final FileUploadService fileUploadService;

    @PostMapping("/{robotId}/cracks")
    public ResponseEntity<Void> detect(@PathVariable String robotId, CrackDetectRequest crackDetectRequest) {

        //ML 서버에 균열 판단 요청
        PredictionMLResponse predictionMLResponse;

        try {
            predictionMLResponse = mlClient.predict(crackDetectRequest.getImage());
        } catch (IOException e) {
            throw new CIOException.CMLCommunicationException();
        }

        //균열 판단율로부터 균열 등록 결정
        if(predictionMLResponse.getAccuracy() >= 50.0) {

            String imageUrl = fileUploadService.upload(crackDetectRequest.getImage());

            crackService.create(robotId,
                new CrackCreateRequest(
                    predictionMLResponse.getAccuracy(),
                    imageUrl,
                    crackDetectRequest
            ));

            return ResponseEntity.created(URI.create("")).build();

        } else {

            return ResponseEntity.noContent().build();

        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RobotCreateResponse create(@RequestBody @Validated RobotCreateRequest robotCreateRequest) {
        return robotService.create(robotCreateRequest);
    }

    @GetMapping("/details")
    public RobotDetailsResponse robotDetails() {
        return robotService.details();
    }
}
