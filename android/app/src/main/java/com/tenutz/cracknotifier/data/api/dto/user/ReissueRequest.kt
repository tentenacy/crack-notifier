package com.tenutz.cracknotifier.data.api.dto.user

data class ReissueRequest(
    val accessToken: String,
    val refreshToken: String,
)
