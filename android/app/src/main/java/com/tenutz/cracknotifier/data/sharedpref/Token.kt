package com.tenutz.cracknotifier.data.sharedpref

import com.chibatching.kotpref.KotprefModel
import com.tenutz.cracknotifier.data.api.dto.common.TokenResponse
import com.tenutz.cracknotifier.util.types.SocialType

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

    fun whenHasAccessToken(callback: () -> Unit): Boolean =
        if(accessToken.isNotBlank()) {
            callback()
            true
        } else {
            false
        }

    fun whenHasOAuthTokenOrNot(
        hasAccessTokenCallback: () -> Unit,
        hasNotAccessTokenCallback: () -> Unit
    ) {
        if (!whenHasAccessToken(hasAccessTokenCallback)) {
            hasNotAccessTokenCallback()
        }
    }
}