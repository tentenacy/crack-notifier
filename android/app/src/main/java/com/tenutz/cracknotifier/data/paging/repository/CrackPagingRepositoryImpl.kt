package com.tenutz.cracknotifier.data.paging.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.tenutz.cracknotifier.data.api.CrackNotifierApi
import com.tenutz.cracknotifier.data.api.dto.common.CommonCondition
import com.tenutz.cracknotifier.data.paging.entity.Cracks
import com.tenutz.cracknotifier.data.paging.source.CracksPagingSource
import com.tenutz.cracknotifier.util.mapper.CracksMapper
import io.reactivex.rxjava3.core.Flowable

class CrackPagingRepositoryImpl(
    private val crackNotifierApi: CrackNotifierApi,
    private val mapper: CracksMapper,
): CrackPagingRepository {

    override fun cracks(cond: CommonCondition): Flowable<PagingData<Cracks.Crack>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                prefetchDistance = 1,
                initialLoadSize = 20
            ),
            pagingSourceFactory = { CracksPagingSource(crackNotifierApi, mapper, cond) }
        ).flowable
    }
}