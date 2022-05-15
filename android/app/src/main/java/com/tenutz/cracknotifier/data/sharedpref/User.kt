package com.tenutz.cracknotifier.data.sharedpref

import com.chibatching.kotpref.KotprefModel
import com.tenutz.cracknotifier.data.api.dto.user.UserDetailsResponse

object User: KotprefModel() {
    var seq by stringPref()
    var email by stringPref()
    var username by stringPref()
    var provider by nullableStringPref()

    fun save(response: UserDetailsResponse) {
        seq = response.seq
        email = response.email
        username = response.username
        provider = response.provider
    }
}