package com.tenutz.cracknotifier.web.api.controller;

import com.tenutz.cracknotifier.web.api.dto.common.TokenResponse;
import com.tenutz.cracknotifier.web.api.dto.user.LoginRequest;
import com.tenutz.cracknotifier.web.api.dto.user.SignupRequest;
import com.tenutz.cracknotifier.web.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@RequestBody @Validated SignupRequest signupRequest) {
        signupRequest.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        userService.signup(signupRequest);
    }

    @PostMapping("/token")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse login(@RequestBody @Validated LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
}
