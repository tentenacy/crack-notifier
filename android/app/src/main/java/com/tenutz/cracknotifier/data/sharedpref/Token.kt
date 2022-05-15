package com.tenutz.cracknotifier.data.sharedpref

import com.chibatching.kotpref.KotprefModel
import com.tenutz.cracknotifier.data.api.dto.common.TokenResponse

object Token: KotprefModel() {
    var grantType by stringPref()
    var accessToken by stringPref()
    var refreshToken by stringPref()
    var accessTokenExpireIn by longPref()

    fun save(response: TokenResponse) {
        grantType = response.grantType
        accessToken = response.accessToken
        refreshToken = response.refreshToken
        accessTokenExpireIn = response.accessTokenExpiresIn
    }
}