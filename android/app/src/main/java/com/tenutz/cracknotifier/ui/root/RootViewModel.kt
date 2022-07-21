package com.tenutz.cracknotifier.ui.root

import com.orhanobut.logger.Logger
import com.tenutz.cracknotifier.data.api.dto.user.FcmTokenRegisterRequest
import com.tenutz.cracknotifier.data.repository.UserRepository
import com.tenutz.cracknotifier.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(
    private val userRepository: UserRepository,
): BaseViewModel() {

    fun registerFcmToken(request: FcmTokenRegisterRequest) {
        userRepository.registerFcmToken(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }) { t ->
                Logger.e("${t}")
            }.addTo(compositeDisposable)
    }
}