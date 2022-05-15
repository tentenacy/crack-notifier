package com.tenutz.cracknotifier.data.repository.crack

import com.tenutz.cracknotifier.data.api.dto.crack.CrackDetailsResponse
import io.reactivex.rxjava3.core.Single

interface CrackRepository {
    fun details(crackId: String): Single<CrackDetailsResponse>
}