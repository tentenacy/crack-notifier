package com.tenutz.cracknotifier.data.api

import com.tenutz.cracknotifier.data.api.dto.common.TokenResponse
import com.tenutz.cracknotifier.data.api.dto.user.LoginRequest
import com.tenutz.cracknotifier.data.api.dto.user.ReissueRequest
import com.tenutz.cracknotifier.data.api.dto.user.SignupRequest
import com.tenutz.cracknotifier.data.api.dto.user.UserDetailsResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    @POST("/users/token")
    fun login(@Body request: LoginRequest): Single<TokenResponse>

    @POST("/users")
    fun signup(@Body request: SignupRequest): Single<Unit>

    @POST("/users/token/expiration")
    fun reissue(@Body request: ReissueRequest): Single<TokenResponse>

    @GET("/users/details")
    fun userDetails(): Single<UserDetailsResponse>
}