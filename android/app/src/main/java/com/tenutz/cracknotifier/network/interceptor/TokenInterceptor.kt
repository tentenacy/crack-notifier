package com.tenutz.cracknotifier.network.interceptor

import com.orhanobut.logger.Logger
import com.tenutz.cracknotifier.data.api.dto.common.ErrorCode
import com.tenutz.cracknotifier.network.observer.TokenExpirationObserver
import com.tenutz.cracknotifier.network.subject.Subject
import com.tenutz.cracknotifier.sharedpref.Token
import com.tenutz.cracknotifier.util.toErrorResponseOrNull
import com.tenutz.cracknotifier.util.toTokenResponseOrNull
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor: Interceptor, Subject<TokenExpirationObserver>() {

    private var accessTokenErrorOccurred = false
    private var refreshTokenErrorOccurred = false

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", if(Token.accessToken.isNotBlank()) "Bearer ${Token.accessToken}" else "")
            .build()
        val response = chain.proceed(request)

        if (!response.isSuccessful) {
            Logger.e("response is not successful")
            response.peekBody(2048).toErrorResponseOrNull()?.apply {
                Logger.e("errorResponse: $this")
                when (code) {
                    ErrorCode.ACCESS_TOKEN_ERROR.code -> {
                        if(!accessTokenErrorOccurred) {
                            notifyTokenExpired()
                            accessTokenErrorOccurred = true
                        }
                    }
                    ErrorCode.REFRESH_TOKEN_ERROR.code -> {
                        if(!refreshTokenErrorOccurred) {
                            notifyRefreshTokenExpired()
                            refreshTokenErrorOccurred = true
                        }
                    }
                }
            }
        } else {
            response.peekBody(2048).toTokenResponseOrNull()?.apply {
                Token.grantType = grantType
                Token.accessToken = accessToken
                Token.refreshToken = refreshToken
                Token.accessTokenExpireIn = accessTokenExpiresIn

                accessTokenErrorOccurred = false
                refreshTokenErrorOccurred = false
            }
        }

        return response
    }

    private fun notifyTokenExpired() {
        observers.forEach(TokenExpirationObserver::onTokenExpired)
    }

    private fun notifyRefreshTokenExpired() {
        observers.forEach(TokenExpirationObserver::onRefreshTokenExpired)
    }
}