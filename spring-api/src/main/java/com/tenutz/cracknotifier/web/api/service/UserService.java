package com.tenutz.cracknotifier.web.api.service;

import com.tenutz.cracknotifier.domain.user.User;
import com.tenutz.cracknotifier.domain.user.UserRepository;
import com.tenutz.cracknotifier.util.EntityUtils;
import com.tenutz.cracknotifier.util.SecurityUtils;
import com.tenutz.cracknotifier.web.api.dto.user.UserDetailsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDetailsResponse details() {
        return new UserDetailsResponse(SecurityUtils.user());
    }
}
