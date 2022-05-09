package com.tenutz.cracknotifier.data.paging.repository

import androidx.paging.PagingData
import com.tenutz.cracknotifier.data.api.dto.common.CommonCondition
import com.tenutz.cracknotifier.data.paging.entity.Cracks
import io.reactivex.rxjava3.core.Flowable

interface CrackPagingRepository {

    fun cracks(cond: CommonCondition): Flowable<PagingData<Cracks.Crack>>
}