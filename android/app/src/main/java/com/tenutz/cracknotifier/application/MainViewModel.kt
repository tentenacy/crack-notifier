package com.tenutz.cracknotifier.application

import com.orhanobut.logger.Logger
import com.tenutz.cracknotifier.data.api.dto.user.ReissueRequest
import com.tenutz.cracknotifier.data.repository.UserRepository
import com.tenutz.cracknotifier.data.sharedpref.OAuthToken
import com.tenutz.cracknotifier.network.interceptor.TokenInterceptor
import com.tenutz.cracknotifier.network.observer.TokenExpirationObserver
import com.tenutz.cracknotifier.data.sharedpref.Token
import com.tenutz.cracknotifier.data.sharedpref.User
import com.tenutz.cracknotifier.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val tokenExpirationObservable: TokenInterceptor,
) : BaseViewModel(), TokenExpirationObserver {

    companion object {
        const val EVENT_LOGOUT = 1000
        const val EVENT_LEAVE = 1001
        const val EVENT_REMAIN = 1002
    }

    init {
        tokenExpirationObservable.registerObserver(this)
    }

    fun autoLogin() {
        OAuthToken.clear()
        OAuthToken.whenHasOAuthTokenOrNot({}) {
            Token.whenHasOAuthTokenOrNot({
                userRepository.details()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        User.save(response)
                        viewEvent(Pair(EVENT_LEAVE, Unit))
                    }) { t ->
                        Logger.e("${t}")
                        logout()
                        viewEvent(Pair(EVENT_REMAIN, Unit))
                    }.addTo(compositeDisposable)
            }) {
                viewEvent(Pair(EVENT_REMAIN, Unit))
            }
        }
    }

    override fun onTokenExpired() {
        userRepository.reissue(ReissueRequest(Token.accessToken, Token.refreshToken))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Token.save(it)
                tokenExpirationObservable.resetTokenErrorOccurred()
            }) { t ->
                Logger.e("${t}")
            }.addTo(compositeDisposable)
    }

    override fun onRefreshTokenExpired() {
        logout()
    }

    override fun onCleared() {
        unregisterObservers()
        super.onCleared()
    }

    private fun unregisterObservers() {
        tokenExpirationObservable.unregisterObserver(this)
    }

    fun logout() {

        Token.clear()

        viewEvent(Pair(EVENT_LOGOUT, Unit))
    }
}