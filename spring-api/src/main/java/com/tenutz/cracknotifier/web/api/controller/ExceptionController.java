package com.tenutz.cracknotifier.web.api.controller;

import com.tenutz.cracknotifier.web.api.exception.security.CSecurityException.CAuthenticationEntryPointException;
import com.tenutz.cracknotifier.web.api.exception.security.CTokenException.CAccessDeniedException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.tenutz.cracknotifier.web.api.exception.business.CEntityNotFoundException.CUserNotFoundException;

@RestController
@RequestMapping("/exception")
@RequiredArgsConstructor
public class ExceptionController {

    @RequestMapping("/entrypoint")
    public void entryPointException() throws Exception {
        throw new CAuthenticationEntryPointException();
    }

    @RequestMapping("/access-denied")
    public void accessDeniedException() {
        throw new CAccessDeniedException();
    }

    @RequestMapping(value = "/user-not-found")
    public void userNotFoundException() {
        throw new CUserNotFoundException();
    }
}
