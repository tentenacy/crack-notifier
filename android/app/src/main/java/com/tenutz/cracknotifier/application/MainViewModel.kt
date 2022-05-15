package com.tenutz.cracknotifier.application

import com.tenutz.cracknotifier.data.repository.UserRepository
import com.tenutz.cracknotifier.network.interceptor.TokenInterceptor
import com.tenutz.cracknotifier.network.observer.TokenExpirationObserver
import com.tenutz.cracknotifier.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val tokenExpirationObservable: TokenInterceptor,
) : BaseViewModel(), TokenExpirationObserver {

    init {
        tokenExpirationObservable.registerObserver(this)
    }

    override fun onTokenExpired() {

    }

    override fun onRefreshTokenExpired() {

    }

    override fun onCleared() {
        unregisterObservers()
        super.onCleared()
    }

    private fun unregisterObservers() {
        tokenExpirationObservable.unregisterObserver(this)
    }
}