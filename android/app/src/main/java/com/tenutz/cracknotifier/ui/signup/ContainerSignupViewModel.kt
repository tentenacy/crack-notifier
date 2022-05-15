package com.tenutz.cracknotifier.ui.signup

import androidx.lifecycle.MutableLiveData
import com.orhanobut.logger.Logger
import com.tenutz.cracknotifier.data.api.dto.user.LoginRequest
import com.tenutz.cracknotifier.data.api.dto.user.SignupRequest
import com.tenutz.cracknotifier.data.repository.UserRepository
import com.tenutz.cracknotifier.data.sharedpref.Token
import com.tenutz.cracknotifier.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ContainerSignupViewModel @Inject constructor(
    private val userRepository: UserRepository,
): BaseViewModel() {

    companion object {
        const val EVENT_NAVIGATE_TO_ROOT = 1000
    }

    val email = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")
    val passwordCheck = MutableLiveData<String>("")
    val name = MutableLiveData<String>("")

    fun signup() {

        if(email.value.isNullOrBlank() || password.value.isNullOrBlank() || name.value.isNullOrBlank()) {
            return
        }

        val request = SignupRequest(email.value!!, password.value!!, name.value!!)

        userRepository.signup(request)
            .flatMap { userRepository.login(LoginRequest(request.email, request.password)) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Token.save(it)
                viewEvent(Pair(EVENT_NAVIGATE_TO_ROOT, Unit))
            }) { t ->
                Logger.e("${t}")
            }
    }
}