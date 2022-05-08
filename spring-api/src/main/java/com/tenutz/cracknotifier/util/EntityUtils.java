package com.tenutz.cracknotifier.util;

import com.tenutz.cracknotifier.domain.crack.Crack;
import com.tenutz.cracknotifier.domain.crack.CrackRepository;
import com.tenutz.cracknotifier.domain.robot.Robot;
import com.tenutz.cracknotifier.domain.robot.RobotRepository;
import com.tenutz.cracknotifier.domain.user.User;
import com.tenutz.cracknotifier.domain.user.UserRepository;
import com.tenutz.cracknotifier.web.api.exception.business.CEntityNotFoundException;

import static com.tenutz.cracknotifier.web.api.exception.business.CEntityNotFoundException.*;

public class EntityUtils {
    public static User userThrowable(UserRepository repository, String seq) {
        return repository.findById(seq)
                .orElseThrow(CUserNotFoundException::new);
    }
    public static Robot robotThrowable(RobotRepository repository, String seq) {
        return repository.findById(seq)
                .orElseThrow(CRobotNotFoundException::new);
    }
    public static Crack crackThrowable(CrackRepository repository, String seq) {
        return repository.findById(seq)
                .orElseThrow(CCrackNotFoundException::new);
    }
}
