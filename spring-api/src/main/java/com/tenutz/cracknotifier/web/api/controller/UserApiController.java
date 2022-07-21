package com.tenutz.cracknotifier.web.api.controller;

import com.tenutz.cracknotifier.util.enums.SocialType;
import com.tenutz.cracknotifier.web.api.dto.common.TokenRequest;
import com.tenutz.cracknotifier.web.api.dto.common.TokenResponse;
import com.tenutz.cracknotifier.web.api.dto.user.*;
import com.tenutz.cracknotifier.web.api.service.AuthService;
import com.tenutz.cracknotifier.web.api.service.RobotService;
import com.tenutz.cracknotifier.web.api.service.UserService;
import com.tenutz.cracknotifier.web.client.dto.SocialProfile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserApiController {

    private final AuthService authService;
    private final UserService userService;
    private final RobotService robotService;
    private final PasswordEncoder passwordEncoder;
//    private final OAuthService oauthService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@RequestBody @Validated SignupRequest signupRequest) {
        signupRequest.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        authService.signup(signupRequest);
    }

    @PostMapping("/token")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse login(@RequestBody @Validated LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    /*@PostMapping("/social/{socialType}/token")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse socialLogin(@PathVariable(name = "socialType") SocialType socialType,
                                     @RequestBody @Validated SocialLoginRequest request) {

        SocialProfile socialProfile = oauthService.profile(socialType, request.getAccessToken());

        //소셜 프로필이 없는 경우 에러
        if(ObjectUtils.isEmpty(socialProfile)) throw new CUserNotFoundException();

        return authService.socialLogin(new LoginRequest(socialProfile.getEmail(), null, socialType.name().toLowerCase()));
    }*/

    @PostMapping("/token/expiration")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse reissue(@RequestBody @Validated TokenRequest request) {
        return authService.reissue(request);
    }

    @GetMapping("/details")
    public UserDetailsResponse userDetails() {
        return userService.details();
    }

    @PostMapping("/robots")
    public void registerRobot(@RequestBody @Validated UserRobotRegisterRequest userRobotRegisterRequest) {
        robotService.registerUserRobot(userRobotRegisterRequest);
    }

    @DeleteMapping("/robots")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deregisterUserRobot() {
        robotService.deregisterUserRobot();
    }

    @PostMapping("/fcm-token")
    public void registerFcmToken(@Valid @RequestBody FcmTokenRegisterRequest request) {
        userService.registerFcmToken(request);
    }
}
