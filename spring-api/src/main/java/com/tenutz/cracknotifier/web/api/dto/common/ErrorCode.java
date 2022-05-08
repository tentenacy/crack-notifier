package com.tenutz.cracknotifier.web.api.dto.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    /**
     * COMMON
     */
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST.value(), "CMM-001", "잘못된 입력입니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(), "CMM-002", "Method Not Allowed"),
    ENTITY_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "CMM-003", "Entity Not Found"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "CMM-004", "Server Error"),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST.value(), "CMM-005", "Invalid Type Value"),
    HANDLE_ACCESS_DENIED(HttpStatus.FORBIDDEN.value(), "CMM-006", "접근이 거부되었습니다."),
    JSON_WRITE_ERROR(HttpStatus.UNAUTHORIZED.value(), "CMM-007", "JSON content that are not pure I/O problems"),

    /**
     * BUSINESS
     * CRN-1xxx
     */
    LOGIN_FAIL(HttpStatus.BAD_REQUEST.value(), "CRN-1000", "존재하지 않는 계정이거나, 잘못된 비밀번호입니다."),
    ALREADY_SIGNEDUP(HttpStatus.BAD_REQUEST.value(), "CRN-1001", "이미 가입한 사용자입니다."),
    USER_NOT_AUTHENTICATION(HttpStatus.UNAUTHORIZED.value(), "CRN-1002", "인증된 사용자가 아닙니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "CRN-1003", "사용자가 존재하지 않습니다."),

    /**
     * SOCIAL
     * CRN-2xxx
     */
    SOCIAL_COMMUNICATION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "CRN-2000", "소셜 인증 과정 중 오류가 발생했습니다."),

    /**
     * SECURITY
     * CRN-3xxx
     */
    ACCESS_TOKEN_ERROR(HttpStatus.UNAUTHORIZED.value(), "CRN-3000", "액세스 토큰이 만료되거나 잘못된 값입니다."),
    REFRESH_TOKEN_ERROR(HttpStatus.UNAUTHORIZED.value(), "CRN-3001", "리프레시 토큰이 만료되거나 잘못된 값입니다."),
    TOKEN_PARSE_ERROR(HttpStatus.UNAUTHORIZED.value(), "CRN-3002", "해석할 수 없는 토큰입니다."),
    SIGNATURE_ERROR(HttpStatus.UNAUTHORIZED.value(), "CRN-3003", "JWT의 생성과 복호화할 때의 비밀키가 서로 다릅니다."),
            ;

    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}