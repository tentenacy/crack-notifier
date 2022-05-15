package com.tenutz.cracknotifier.data.api

import com.tenutz.cracknotifier.data.api.dto.common.TokenResponse
import com.tenutz.cracknotifier.data.api.dto.user.LoginRequest
import com.tenutz.cracknotifier.data.api.dto.user.SignupRequest
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("/users/token")
    fun login(@Body request: LoginRequest): Single<TokenResponse>

    @POST("/users")
    fun signup(@Body request: SignupRequest): Single<Unit>
}