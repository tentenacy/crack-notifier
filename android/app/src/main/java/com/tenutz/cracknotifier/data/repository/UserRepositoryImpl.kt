package com.tenutz.cracknotifier.data.repository

import com.tenutz.cracknotifier.data.api.CrackNotifierApi
import com.tenutz.cracknotifier.data.api.dto.common.TokenResponse
import com.tenutz.cracknotifier.data.api.dto.user.LoginRequest
import com.tenutz.cracknotifier.data.api.dto.user.ReissueRequest
import com.tenutz.cracknotifier.data.api.dto.user.SignupRequest
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val crackNotifierApi: CrackNotifierApi,
): UserRepository {

    override fun login(request: LoginRequest): Single<TokenResponse> = crackNotifierApi.login(request)

    override fun signup(request: SignupRequest): Single<Unit> = crackNotifierApi.signup(request)

    override fun reissue(request: ReissueRequest): Single<TokenResponse> = crackNotifierApi.reissue(request)
}