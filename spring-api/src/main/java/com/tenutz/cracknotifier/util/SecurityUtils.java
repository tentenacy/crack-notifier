package com.tenutz.cracknotifier.util;

import com.tenutz.cracknotifier.domain.robot.Robot;
import com.tenutz.cracknotifier.domain.robot.RobotRepository;
import com.tenutz.cracknotifier.domain.user.User;
import com.tenutz.cracknotifier.domain.user.UserRepository;
import com.tenutz.cracknotifier.web.api.exception.business.CEntityNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static User user() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static User userThrowable(UserRepository userRepository) {
        return userRepository.findById(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSeq())
                .orElseThrow(CEntityNotFoundException.CUserNotFoundException::new);
    }

    public static Robot robotThrowable(RobotRepository robotRepository, String robotSeq) {
        return robotRepository.findById(robotSeq).orElseThrow(CEntityNotFoundException.CRobotNotFoundException::new);
    }
}
