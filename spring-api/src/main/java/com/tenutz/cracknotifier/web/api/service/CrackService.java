package com.tenutz.cracknotifier.web.api.service;

import com.tenutz.cracknotifier.domain.crack.Crack;
import com.tenutz.cracknotifier.domain.crack.CrackRepository;
import com.tenutz.cracknotifier.domain.robot.Robot;
import com.tenutz.cracknotifier.domain.robot.RobotRepository;
import com.tenutz.cracknotifier.util.EntityUtils;
import com.tenutz.cracknotifier.util.FileUtils;
import com.tenutz.cracknotifier.web.api.dto.crack.CrackCreateRequest;
import com.tenutz.cracknotifier.web.api.dto.crack.CrackDetectRequest;
import com.tenutz.cracknotifier.web.api.exception.business.CEntityNotFoundException;
import com.tenutz.cracknotifier.web.api.exception.business.CEntityNotFoundException.CRobotNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CrackService {

    private final RobotRepository robotRepository;
    private final CrackRepository crackRepository;

    @Transactional
    public void create(String robotSeq, CrackCreateRequest request) {
        Robot foundRobot = EntityUtils.robotThrowable(robotRepository, robotSeq);
        crackRepository.save(Crack.create(
                foundRobot,
                request.getAccuracy(),
                request.getImageUrl(),
                request.getLatitude(),
                request.getLongitude(),
                request.getX(),
                request.getY(),
                request.getAddress()
        ));
    }

    @Transactional
    public String delete(String crackSeq) {
        Crack foundCrack = EntityUtils.crackThrowable(crackRepository, crackSeq);
        crackRepository.delete(foundCrack);
        return FileUtils.extractFileNameFrom(foundCrack.getImage());
    }
}
