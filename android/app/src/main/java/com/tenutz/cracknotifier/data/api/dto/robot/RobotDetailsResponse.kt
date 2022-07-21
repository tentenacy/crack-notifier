package com.tenutz.cracknotifier.data.api.dto.robot

import java.util.*

data class RobotDetailsResponse(
    val seq: String,
    val modelNo: String,
    val image: String?,
    val width: Float,
    val depth: Float,
    val height: Float,
    val weight: Float,
    val createdAt: Date,
    val lastModifiedAt: Date,
)
