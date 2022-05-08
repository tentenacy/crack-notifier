package com.tenutz.cracknotifier.web.api.exception.security;

import com.tenutz.cracknotifier.web.api.dto.common.ErrorCode;
import lombok.Getter;

@Getter
public class CSecurityException extends RuntimeException {

    private ErrorCode errorCode;

    public CSecurityException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public static class CAuthenticationEntryPointException extends CSecurityException {

        public CAuthenticationEntryPointException() {
            super(ErrorCode.ACCESS_TOKEN_ERROR);
        }
    }

}
