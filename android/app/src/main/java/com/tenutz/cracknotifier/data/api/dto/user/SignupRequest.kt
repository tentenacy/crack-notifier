package com.tenutz.cracknotifier.data.api.dto.user

data class SignupRequest(
    val email: String,
    val password: String,
    val username: String,
)
