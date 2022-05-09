package com.tenutz.cracknotifier.network.observer

interface TokenExpirationObserver {
    fun onTokenExpired()
    fun onRefreshTokenExpired()
}