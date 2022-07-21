package com.tenutz.cracknotifier.ui.root.robotcurrentsituation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.orhanobut.logger.Logger
import com.tenutz.cracknotifier.data.api.dto.robot.DrivingInformationResponse
import com.tenutz.cracknotifier.data.api.dto.robot.RobotDetailsResponse
import com.tenutz.cracknotifier.data.repository.robot.RobotRepository
import com.tenutz.cracknotifier.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class RobotCurrentSituationViewModel @Inject constructor(
    private val robotRepository: RobotRepository,
): BaseViewModel() {

    private val _robotDetails = MutableLiveData<RobotDetailsResponse>()
    val robotDetails: LiveData<RobotDetailsResponse> = _robotDetails

    private val _robotDrivingInformation = MutableLiveData<DrivingInformationResponse>()
    val robotDrivingInformation: LiveData<DrivingInformationResponse> = _robotDrivingInformation

    fun robotDetails() {
        robotRepository.robotDetails()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _robotDetails.value = it
            }) { t ->
                Logger.e("${t}")
            }.addTo(compositeDisposable)
    }

    fun robotDrivingInformation() {
        Observable.interval(1, TimeUnit.SECONDS)
            .flatMap {
                robotRepository.drivingInformation().toObservable()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _robotDrivingInformation.value = it
            }) { t ->
                Logger.e("${t}")
            }.addTo(compositeDisposable)
    }

}