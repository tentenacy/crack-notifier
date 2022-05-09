package com.tenutz.cracknotifier.data.api.dto.crack

import android.os.Parcelable
import com.tenutz.cracknotifier.data.api.dto.common.Address
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class CrackDetailsResponse(
    val seq: String,
    val robotSeq: String,
    val accuracy: Float,
    val address: Address,
    val imageUrl: String? = null,
    val latitude: Float,
    val longitude: Float,
    val x: Float,
    val y: Float,
    val createdAt: Date?,
    val lastModifiedAt: Date?,
): Parcelable
