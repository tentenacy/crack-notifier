package com.tenutz.cracknotifier.repository.crack

import com.tenutz.cracknotifier.data.api.CrackNotifierApi
import com.tenutz.cracknotifier.data.api.dto.crack.CrackDetailsResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CrackRepositoryImpl @Inject constructor(
    private val crackNotifierApi: CrackNotifierApi,
): CrackRepository {

    override fun details(crackId: String): Single<CrackDetailsResponse> = crackNotifierApi.details(crackId)
}