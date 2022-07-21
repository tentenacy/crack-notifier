package com.tenutz.cracknotifier.data.api.dto.robot

import java.util.*

data class DrivingInformationResponse(
    val seq: String,
    val latitude: Float,
    val longitude: Float,
    val x: Float,
    val y: Float,
    val addressName: String?,
    val roadAddressName: String?,
    val region1DepthName: String?,
    val region2DepthName: String?,
    val region3DepthName: String?,
    val zoneNo: String?,
    val velocity: Float,
    val propellerVelocity: Float,
    val slopeX: Float,
    val slopeY: Float,
    val slopeZ: Float,
    val batteryVoltage: Float,
    val lastModifiedAt: Date,
)
