package com.tenutz.cracknotifier.data.paging.source

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.tenutz.cracknotifier.data.api.CrackNotifierApi
import com.tenutz.cracknotifier.data.api.dto.common.CommonCondition
import com.tenutz.cracknotifier.data.paging.entity.Cracks
import com.tenutz.cracknotifier.util.applyRetryPolicy
import com.tenutz.cracknotifier.util.costant.policy.RetryPolicyConstant
import com.tenutz.cracknotifier.util.mapper.CracksMapper
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CracksPagingSource @Inject constructor(
    private val crackNotifierApi: CrackNotifierApi,
    private val mapper: CracksMapper,
    private val cond: CommonCondition,
): RxPagingSource<Int, Cracks.Crack>() {

    override fun getRefreshKey(state: PagingState<Int, Cracks.Crack>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Cracks.Crack>> {
        val position = params.key ?: 0
        return crackNotifierApi.cracks(
            page = position,
            size = params.loadSize,
            dateFrom = cond.dateFrom,
            dateTo = cond.dateTo,
        )
            .delay(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .map { response ->
                mapper.transform(response)
            }
            .map { messages ->
                toLoadResult(messages, position)
            }
            .compose(
                applyRetryPolicy(
                    RetryPolicyConstant.TIMEOUT,
                    RetryPolicyConstant.NETWORK,
                    RetryPolicyConstant.SERVICE_UNAVAILABLE,
                    RetryPolicyConstant.ACCESS_TOKEN_EXPIRED
                ) { LoadResult.Error(it) })
    }

    private fun toLoadResult(data: Cracks, position: Int): LoadResult<Int, Cracks.Crack> {
        return LoadResult.Page(
            data = data.cracks,
            prevKey = if(position == 0) null else position - 1,
            nextKey = if(data.endOfPage) null else position + 1,
        )
    }
}