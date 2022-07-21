package com.tenutz.cracknotifier.data.api

import com.tenutz.cracknotifier.data.api.dto.robot.DrivingInformationResponse
import com.tenutz.cracknotifier.data.api.dto.robot.RobotDetailsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface RobotApi {

    @GET("/robots/details")
    fun robotDetails(): Single<RobotDetailsResponse>

    @GET("/robots/driving-information")
    fun drivingInformation(): Single<DrivingInformationResponse>
}