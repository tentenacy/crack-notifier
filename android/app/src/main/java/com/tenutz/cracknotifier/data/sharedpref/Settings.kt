package com.tenutz.cracknotifier.data.sharedpref

import com.chibatching.kotpref.KotprefModel
import com.tenutz.cracknotifier.data.api.dto.common.TokenResponse
import com.tenutz.cracknotifier.util.types.SocialType

object Settings: KotprefModel() {
    var pushNotification by booleanPref(true)
    var pushNotificationCrackRegistration by booleanPref(true)
    var pushNotificationBattery by booleanPref(true)
}