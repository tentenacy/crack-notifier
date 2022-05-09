package com.tenutz.cracknotifier.repository.crack

import com.tenutz.cracknotifier.data.api.dto.crack.CrackDetailsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Path

interface CrackRepository {
    fun details(crackId: String): Single<CrackDetailsResponse>
}