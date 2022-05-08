package com.tenutz.cracknotifier.web.api.service;

import com.tenutz.cracknotifier.domain.user.User;
import com.tenutz.cracknotifier.domain.user.UserRepository;
import com.tenutz.cracknotifier.web.api.dto.user.SignupRequest;
import com.tenutz.cracknotifier.web.api.exception.business.CInvalidValueException.CAlreadySignedupException;
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

    @Transactional
    public void signup(SignupRequest req) {
        User user = User.create(req.getEmail(), req.getPassword(), req.getUsername(), req.getProvider());
        userRepository.findByEmail(user.getEmail()).ifPresent((foundUser) -> {
            throw new CAlreadySignedupException();
        });
        userRepository.save(user);
    }
}
