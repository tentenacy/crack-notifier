package com.tenutz.cracknotifier.data.repository

import com.tenutz.cracknotifier.data.api.dto.common.TokenResponse
import com.tenutz.cracknotifier.data.api.dto.user.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body

interface UserRepository {

    fun login(request: LoginRequest): Single<TokenResponse>

    fun signup(request: SignupRequest): Single<Unit>

    fun reissue(request: ReissueRequest): Single<TokenResponse>

    fun details(): Single<UserDetailsResponse>

    fun registerFcmToken(request: FcmTokenRegisterRequest): Single<Unit>
}