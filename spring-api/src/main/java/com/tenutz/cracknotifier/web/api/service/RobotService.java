package com.tenutz.cracknotifier.web.api.service;

import com.tenutz.cracknotifier.domain.robot.Robot;
import com.tenutz.cracknotifier.domain.robot.RobotRepository;
import com.tenutz.cracknotifier.domain.user.UserRepository;
import com.tenutz.cracknotifier.util.EntityUtils;
import com.tenutz.cracknotifier.util.SecurityUtils;
import com.tenutz.cracknotifier.web.api.dto.robot.RobotCreateRequest;
import com.tenutz.cracknotifier.web.api.dto.robot.RobotCreateResponse;
import com.tenutz.cracknotifier.web.api.dto.robot.RobotDetailsResponse;
import com.tenutz.cracknotifier.web.api.dto.user.UserRobotRegisterRequest;
import com.tenutz.cracknotifier.web.api.exception.business.CEntityNotFoundException.CRobotNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RobotService {

    private final RobotRepository robotRepository;
    private final UserRepository userRepository;

    @Transactional
    public RobotCreateResponse create(RobotCreateRequest request) {
        return new RobotCreateResponse(robotRepository.save(Robot.create(
                createModelNumber(),
                request.getWidth(),
                request.getDepth(),
                request.getHeight(),
                request.getWeight()
                )));
    }

    public RobotDetailsResponse details() {
        Robot foundRobot = Optional.ofNullable(EntityUtils.userThrowable(userRepository, SecurityUtils.user().getSeq()).getRobot()).orElseThrow(RuntimeException::new);
        return new RobotDetailsResponse(foundRobot);
    }

    @Transactional
    public void registerUserRobot(UserRobotRegisterRequest request) {
        Robot foundRobot = robotRepository.findByModelNo(request.getModelNo()).orElseThrow(CRobotNotFoundException::new);
        EntityUtils.userThrowable(userRepository, SecurityUtils.user().getSeq()).registerRobot(foundRobot);
    }

    @Transactional
    public void deregisterUserRobot() {
        EntityUtils.userThrowable(userRepository, SecurityUtils.user().getSeq()).deregisterRobot();
    }

    private String createModelNumber() {
        return new Random().ints(48, 122)
                .filter(i -> (i >= 48 && i <= 57) || (i >= 65 && i <= 90) || (i >= 97 && i <= 122))
                .limit(24)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
