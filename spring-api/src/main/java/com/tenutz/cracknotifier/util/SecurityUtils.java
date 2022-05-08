package com.tenutz.cracknotifier.util;

import com.tenutz.cracknotifier.domain.user.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static User user() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
