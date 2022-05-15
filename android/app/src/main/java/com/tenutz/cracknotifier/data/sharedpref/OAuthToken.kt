package com.tenutz.cracknotifier.data.sharedpref

import com.chibatching.kotpref.KotprefModel
import com.tenutz.cracknotifier.util.types.SocialType

object OAuthToken: KotprefModel() {
    var accessToken by stringPref()
    var refreshToken by nullableStringPref()
    var socialType by stringPref()

    fun whenHasOAuthTokenOrNot(
        hasAccessTokenCallback: (SocialType) -> Unit,
        hasNotAccessTokenCallback: () -> Unit
    ) {
        if (!whenHasOAuthToken(hasAccessTokenCallback)) {
            hasNotAccessTokenCallback()
        }
    }

    fun whenHasOAuthToken(hasAccessTokenCallback: (SocialType) -> Unit): Boolean =
        if (accessToken.isNotBlank()) {
            socialType.getSocialType()?.let { hasAccessTokenCallback(it) }
            true
        } else {
            false
        }
}

fun String.getSocialType(): SocialType? = when(this) {
    "naver" -> {
        SocialType.NAVER
    }
    "kakao" -> {
        SocialType.KAKAO
    }
    "google" -> {
        SocialType.GOOGLE
    }
    "facebook" -> {
        SocialType.FACEBOOK
    }
    else -> null
}