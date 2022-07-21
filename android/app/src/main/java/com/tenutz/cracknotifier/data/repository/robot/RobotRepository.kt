package com.tenutz.cracknotifier.data.repository.robot

import com.tenutz.cracknotifier.data.api.dto.robot.DrivingInformationResponse
import com.tenutz.cracknotifier.data.api.dto.robot.RobotDetailsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface RobotRepository {

    fun robotDetails(): Single<RobotDetailsResponse>

    fun drivingInformation(): Single<DrivingInformationResponse>
}