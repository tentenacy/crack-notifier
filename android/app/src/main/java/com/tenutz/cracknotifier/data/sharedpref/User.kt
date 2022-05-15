package com.tenutz.cracknotifier.data.sharedpref

import com.chibatching.kotpref.Kotpref
import com.chibatching.kotpref.KotprefModel

object User: KotprefModel() {
    var seq by stringPref()
    var email by stringPref()
    var username by stringPref()
    var provider by nullableStringPref()
}