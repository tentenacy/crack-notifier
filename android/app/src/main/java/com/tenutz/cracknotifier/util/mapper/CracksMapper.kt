package com.tenutz.cracknotifier.util.mapper

import com.orhanobut.logger.Logger
import com.tenutz.cracknotifier.data.api.dto.common.PageResponse
import com.tenutz.cracknotifier.data.api.dto.crack.CracksResponse
import com.tenutz.cracknotifier.data.paging.entity.Cracks
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CracksMapper @Inject constructor() {
    fun transform(response: PageResponse<CracksResponse>): Cracks {
        return with(response) {
            Cracks(
                total = totalPages,
                page = number,
                cracks = content.map {
                    Cracks.Crack(
                        seq = it.seq,
                        imageUrl = it.imageUrl,
                        createdAt = it.createdAt,
                        accuracy = it.accuracy,
                        region3DepthName = it.region3DepthName,
                    )
                }
            )
        }.also { Logger.i("cracksResponse: ${it}") }
    }
}