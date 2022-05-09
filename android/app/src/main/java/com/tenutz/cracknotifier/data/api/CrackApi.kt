package com.tenutz.cracknotifier.data.api

import com.tenutz.cracknotifier.data.api.dto.common.PageResponse
import com.tenutz.cracknotifier.data.api.dto.crack.CrackDetailsResponse
import com.tenutz.cracknotifier.data.api.dto.crack.CracksResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CrackApi {

    @GET("/cracks")
    fun cracks(
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
        @Query("sort") sort: String? = "createdAt,desc",
        @Query("dateTimeFrom") dateFrom: String? = null,
        @Query("dateTimeTo") dateTo: String? = null,
    ): Single<PageResponse<CracksResponse>>

    @GET("/cracks/{crackId}/details")
    fun details(@Path("crackId") crackId: String): Single<CrackDetailsResponse>
}