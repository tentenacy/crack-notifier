package com.tenutz.cracknotifier.ui.emaillogin

import com.orhanobut.logger.Logger
import com.tenutz.cracknotifier.data.api.dto.common.ErrorCode
import com.tenutz.cracknotifier.data.api.dto.user.LoginRequest
import com.tenutz.cracknotifier.data.repository.UserRepository
import com.tenutz.cracknotifier.data.sharedpref.Token
import com.tenutz.cracknotifier.data.sharedpref.User
import com.tenutz.cracknotifier.ui.base.BaseViewModel
import com.tenutz.cracknotifier.util.toErrorResponseOrNull
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class EmailLoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : BaseViewModel() {

    companion object {
        const val EVENT_NAVIGATE_TO_ROOT = 1000
        const val EVENT_TOAST_LOGIN_FAILED = 1001
    }

    fun login(request: LoginRequest) {

        userRepository.login(request)
            .flatMap {
                Token.save(it)
                userRepository.details()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                User.save(it)
                viewEvent(Pair(EVENT_NAVIGATE_TO_ROOT, Unit))
            }) { t ->
                Logger.e("${t}")
                t.toErrorResponseOrNull()?.let {
                    when(it.code) {
                        ErrorCode.LOGIN_FAIL.code -> {
                            viewEvent(Pair(EVENT_TOAST_LOGIN_FAILED, Unit))
                        }
                    }
                }
            }.addTo(compositeDisposable)
    }
}