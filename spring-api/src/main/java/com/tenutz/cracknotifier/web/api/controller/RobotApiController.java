package com.tenutz.cracknotifier.web.api.controller;

import com.tenutz.cracknotifier.web.api.dto.crack.CrackCreateRequest;
import com.tenutz.cracknotifier.web.api.dto.crack.CrackDetectRequest;
import com.tenutz.cracknotifier.web.api.dto.fcm.FcmRequest;
import com.tenutz.cracknotifier.web.api.dto.robot.*;
import com.tenutz.cracknotifier.web.api.exception.business.CEntityNotFoundException;
import com.tenutz.cracknotifier.web.api.exception.io.CIOException.CMLCommunicationException;
import com.tenutz.cracknotifier.web.api.exception.social.CSocialException;
import com.tenutz.cracknotifier.web.api.service.CrackService;
import com.tenutz.cracknotifier.web.api.service.RobotService;
import com.tenutz.cracknotifier.web.api.service.UserService;
import com.tenutz.cracknotifier.web.api.service.cloud.FileUploadService;
import com.tenutz.cracknotifier.web.client.MLClient;
import com.tenutz.cracknotifier.web.client.dto.ml.PredictionMLResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/robots")
@RequiredArgsConstructor
public class RobotApiController {

    private final RobotService robotService;
    private final CrackService crackService;
    private final MLClient mlClient;
    private final FileUploadService fileUploadService;
    private final WebClient webClient;
    private final UserService userService;

    @Value("${ext.firebase.server-key}")
    private String fcmServerKey;

    @PostMapping("/{robotId}/cracks")
    public ResponseEntity<Void> detect(@PathVariable String robotId, CrackDetectRequest crackDetectRequest) {
        //ML 서버에 균열 판단 요청
        PredictionMLResponse predictionMLResponse;
        try {
            predictionMLResponse = mlClient.predict(crackDetectRequest.getImage());
        } catch (IOException e) {
            throw new CMLCommunicationException();
        }

        log.info("accuracy = "+predictionMLResponse.getAccuracy());

        //균열 판단율로부터 균열 등록 결정
        if(predictionMLResponse.getAccuracy() >= 50.0) {
            String imageUrl = fileUploadService.upload(crackDetectRequest.getImage());
            crackService.create(robotId,
                new CrackCreateRequest(
                    predictionMLResponse.getAccuracy(),
                    imageUrl,
                    crackDetectRequest
            ));

            try {
                webClient.post()
                        .uri("https://fcm.googleapis.com/fcm/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(new FcmRequest(
                                Collections.singletonList(userService.fcmToken(robotId)),
                                "high",
                                new FcmRequest.Data(
                                        "crack_registration",
                                        "[" + crackDetectRequest.getRegion3DepthName() + "] 새 균열 등록됨",
                                        crackDetectRequest.getRegion3DepthName() + "에 위치한 벽면 x: "+crackDetectRequest.getX() + "cm, y: "+crackDetectRequest.getY() + "cm 상에서 새로운 균열이 발견되었습니다."
                                )
                        ))
                        .header("Authorization", "key=" + fcmServerKey)
                        .retrieve()
                        .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(CSocialException.CSocialCommunicationException::new))
                        .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(CSocialException.CSocialCommunicationException::new))
                        .bodyToMono(Void.class)
                        .block();
            } catch (CEntityNotFoundException.CUserNotFoundException ex) {
                ex.printStackTrace();
            }

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

    @PutMapping("/{robotId}/driving-information")
    public void updateDrivingInformation(@PathVariable String robotId, @Valid @RequestBody DrivingInformationUpdateRequest request) {
        robotService.updateDrivingInformation(robotId, request);
        boolean batteryNotified = robotService.batteryNotified(robotId);
        if(request.getBatteryVoltage() <= 0.2 && !batteryNotified) {
            try {
                webClient.post()
                        .uri("https://fcm.googleapis.com/fcm/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(new FcmRequest(
                                Collections.singletonList(userService.fcmToken(robotId)),
                                "high",
                                new FcmRequest.Data(
                                        "battery",
                                        "[로봇] 배터리 잔량 20%",
                                        "로봇의 배터리가 20% 미만이므로 운행을 멈추고, 배터리를 충전해주세요."
                                )
                        ))
                        .header("Authorization", "key=" + fcmServerKey)
                        .retrieve()
                        .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(CSocialException.CSocialCommunicationException::new))
                        .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(CSocialException.CSocialCommunicationException::new))
                        .bodyToMono(Void.class)
                        .block();
                robotService.notifyBattery(robotId);
            } catch (CEntityNotFoundException.CUserNotFoundException ex) {
                ex.printStackTrace();
            }
        } else if(request.getBatteryVoltage() > 0.2 && batteryNotified) {
            robotService.resetBatteryNotification(robotId);
        }
    }

    @GetMapping("/driving-information")
    public RobotDrivingInformationResponse drivingInformation() {
        return robotService.drivingInformation();
    }
}
