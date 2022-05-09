package com.tenutz.cracknotifier.data.api.dto.common

import javax.net.ssl.HttpsURLConnection

enum class ErrorCode(
    val status: Int,
    val code: String,
    val message: String
) {

    /**
     * COMMON
     */
    INVALID_INPUT_VALUE(
        HttpsURLConnection.HTTP_BAD_REQUEST,
        "CMM-001",
        "잘못된 입력입니다."
    ),
    METHOD_NOT_ALLOWED(
        HttpsURLConnection.HTTP_BAD_METHOD,
        "CMM-002",
        "Method Not Allowed"
    ),
    ENTITY_NOT_FOUND(
        HttpsURLConnection.HTTP_BAD_REQUEST,
        "CMM-003",
        "Entity Not Found"
    ),
    INTERNAL_SERVER_ERROR(
        HttpsURLConnection.HTTP_INTERNAL_ERROR,
        "CMM-004",
        "Server Error"
    ),
    INVALID_TYPE_VALUE(
        HttpsURLConnection.HTTP_BAD_REQUEST,
        "CMM-005",
        "Invalid Type Value"
    ),
    HANDLE_ACCESS_DENIED(HttpsURLConnection.HTTP_FORBIDDEN, "CMM-006", "접근이 거부되었습니다."),
    JSON_WRITE_ERROR(
        HttpsURLConnection.HTTP_UNAUTHORIZED,
        "CMM-007",
        "JSON content that are not pure I/O problems"
    ),

    /**
     * SOCIAL
     */
    SOCIAL_COMMUNICATION_ERROR(
        HttpsURLConnection.HTTP_INTERNAL_ERROR,
        "CNF-2000",
        "소셜 인증 과정 중 오류가 발생했습니다."
    ),

    /**
     * SECURITY
     */
    ACCESS_TOKEN_ERROR(
        HttpsURLConnection.HTTP_UNAUTHORIZED,
        "CNF-3000",
        "액세스 토큰이 만료되거나 잘못된 값입니다."
    ),
    REFRESH_TOKEN_ERROR(
        HttpsURLConnection.HTTP_UNAUTHORIZED,
        "CNF-3001",
        "리프레시 토큰이 만료되거나 잘못된 값입니다."
    ),
    TOKEN_PARSE_ERROR(HttpsURLConnection.HTTP_UNAUTHORIZED, "CNF-3002", "해석할 수 없는 토큰입니다."),
    SIGNATURE_ERROR(HttpsURLConnection.HTTP_UNAUTHORIZED, "CNF-3003", "JWT의 생성과 복호화할 때의 비밀키가 서로 다릅니다."),

    /**
     * BUSINESS
     */
//    MEMBERINFO_NOT_FOUND(HttpsURLConnection.HTTP_BAD_REQUEST, "CNF-1018", "사용자 정보가 존재하지 않습니다."),
}