package com.tenutz.cracknotifier.data.api.dto.crack

import java.util.*

data class CracksResponse(
    val seq: String,
    val imageUrl: String? = null,
    val createdAt: Date? = null,
    val accuracy: Float,
    val region3DepthName: String? = null,
)