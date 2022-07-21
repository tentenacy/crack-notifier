package com.tenutz.cracknotifier.web.api.service;

import com.tenutz.cracknotifier.domain.robot.Robot;
import com.tenutz.cracknotifier.domain.robot.RobotRepository;
import com.tenutz.cracknotifier.domain.user.User;
import com.tenutz.cracknotifier.domain.user.UserRepository;
import com.tenutz.cracknotifier.util.SecurityUtils;
import com.tenutz.cracknotifier.web.api.dto.user.FcmTokenRegisterRequest;
import com.tenutz.cracknotifier.web.api.dto.user.UserDetailsResponse;
import com.tenutz.cracknotifier.web.api.exception.business.CEntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RobotRepository robotRepository;

    public UserDetailsResponse details() {
        return new UserDetailsResponse(SecurityUtils.user());
    }

    @Transactional
    public void registerFcmToken(FcmTokenRegisterRequest request) {
        User foundUser = SecurityUtils.userThrowable(userRepository);
        foundUser.updateFcmToken(request.getFcmToken());
    }

    public String fcmToken(String robotSeq) {
        Robot foundRobot = SecurityUtils.robotThrowable(robotRepository, robotSeq);
        User foundUser = userRepository.findByRobot(foundRobot).orElseThrow(CEntityNotFoundException.CUserNotFoundException::new);
        return foundUser.getFcmToken();
    }
}
