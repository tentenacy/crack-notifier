package com.tenutz.cracknotifier.data.repository.robot

import com.tenutz.cracknotifier.data.api.CrackNotifierApi
import com.tenutz.cracknotifier.data.api.dto.robot.DrivingInformationResponse
import com.tenutz.cracknotifier.data.api.dto.robot.RobotDetailsResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RobotRepositoryImpl @Inject constructor(
    private val crackNotifierApi: CrackNotifierApi,
): RobotRepository {

    override fun robotDetails(): Single<RobotDetailsResponse> = crackNotifierApi.robotDetails()

    override fun drivingInformation(): Single<DrivingInformationResponse> = crackNotifierApi.drivingInformation()
}