package com.tenutz.cracknotifier.web.api.service;

import com.tenutz.cracknotifier.domain.robot.Robot;
import com.tenutz.cracknotifier.domain.robot.RobotDrivingInformation;
import com.tenutz.cracknotifier.domain.robot.RobotDrivingInformationRepository;
import com.tenutz.cracknotifier.domain.robot.RobotRepository;
import com.tenutz.cracknotifier.domain.user.User;
import com.tenutz.cracknotifier.domain.user.UserRepository;
import com.tenutz.cracknotifier.util.EntityUtils;
import com.tenutz.cracknotifier.util.SecurityUtils;
import com.tenutz.cracknotifier.web.api.dto.robot.*;
import com.tenutz.cracknotifier.web.api.dto.user.UserRobotRegisterRequest;
import com.tenutz.cracknotifier.web.api.exception.business.CEntityNotFoundException;
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
    private final RobotDrivingInformationRepository robotDrivingInformationRepository;

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
        User foundUser = EntityUtils.userThrowable(userRepository, SecurityUtils.user().getSeq());
        Robot foundRobot = Optional.ofNullable(foundUser.getRobot()).orElseThrow(CRobotNotFoundException::new);
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

    @Transactional
    public void updateDrivingInformation(DrivingInformationUpdateRequest request) {
        User foundUser = SecurityUtils.userThrowable(userRepository);
        Robot foundRobot = Optional.ofNullable(foundUser.getRobot()).orElseThrow(CRobotNotFoundException::new);
        RobotDrivingInformation robotDrivingInformation = robotDrivingInformationRepository.findByRobot(foundRobot).orElseGet(() -> robotDrivingInformationRepository.save(RobotDrivingInformation.create(foundRobot)));
        robotDrivingInformation.update(
                request.getLatitude(),
                request.getLongitude(),
                request.getX(),
                request.getY(),
                request.getAddressName(),
                request.getRoadAddressName(),
                request.getRegion1DepthName(),
                request.getRegion2DepthName(),
                request.getRegion3DepthName(),
                request.getZoneNo(),
                request.getVelocity(),
                request.getPropellerVelocity(),
                request.getSlopeX(),
                request.getSlopeY(),
                request.getSlopeZ(),
                request.getBatteryVoltage()
        );
    }

    public RobotDrivingInformationResponse drivingInformation() {
        User foundUser = SecurityUtils.userThrowable(userRepository);
        Robot foundRobot = Optional.ofNullable(foundUser.getRobot()).orElseThrow(CRobotNotFoundException::new);
        RobotDrivingInformation foundRobotDrivingInformation = robotDrivingInformationRepository.findByRobot(foundRobot).orElseThrow(CEntityNotFoundException.CRobotDrivingInformationNotFoundException::new);
        return RobotDrivingInformationResponse.of(foundRobotDrivingInformation);
    }
}
