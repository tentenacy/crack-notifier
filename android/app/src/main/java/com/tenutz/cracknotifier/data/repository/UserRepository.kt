package com.tenutz.cracknotifier.data.repository

import com.tenutz.cracknotifier.data.api.dto.common.TokenResponse
import com.tenutz.cracknotifier.data.api.dto.user.LoginRequest
import io.reactivex.rxjava3.core.Single

interface UserRepository {

    fun login(request: LoginRequest): Single<TokenResponse>
}