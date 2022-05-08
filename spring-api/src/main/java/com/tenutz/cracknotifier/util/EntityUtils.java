package com.tenutz.cracknotifier.util;

import com.tenutz.cracknotifier.domain.user.User;
import com.tenutz.cracknotifier.domain.user.UserRepository;
import com.tenutz.cracknotifier.web.api.exception.business.CEntityNotFoundException;

public class EntityUtils {
    public static User userThrowable(UserRepository userRepository, String seq) {
        return userRepository.findById(seq)
                .orElseThrow(CEntityNotFoundException.CUserNotFoundException::new);
    }
}
