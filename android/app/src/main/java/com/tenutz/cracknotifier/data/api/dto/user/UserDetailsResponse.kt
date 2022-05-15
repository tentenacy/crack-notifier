package com.tenutz.cracknotifier.data.api.dto.user

data class UserDetailsResponse(
    val seq: String,
    val email: String,
    val username: String,
    val provider: String? = null,
)
