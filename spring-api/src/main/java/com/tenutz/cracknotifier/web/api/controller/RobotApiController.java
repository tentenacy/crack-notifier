package com.tenutz.cracknotifier.web.api.controller;

import com.tenutz.cracknotifier.web.api.dto.robot.RobotCreateRequest;
import com.tenutz.cracknotifier.web.api.dto.robot.RobotCreateResponse;
import com.tenutz.cracknotifier.web.api.dto.robot.RobotDetailsResponse;
import com.tenutz.cracknotifier.web.api.dto.user.UserDetailsResponse;
import com.tenutz.cracknotifier.web.api.service.RobotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/robots")
@RequiredArgsConstructor
public class RobotApiController {

    private final RobotService robotService;

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
