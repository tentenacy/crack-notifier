package com.tenutz.cracknotifier.data.api

import com.tenutz.cracknotifier.BuildConfig
import retrofit2.Retrofit
import retrofit2.create

interface CrackNotifierApi: CrackApi {
    companion object {
        fun create(retrofitBuilder: Retrofit.Builder): CrackNotifierApi {
            return retrofitBuilder
                .baseUrl("http://${BuildConfig.IP}:${BuildConfig.PORT}")
                .build()
                .create()
        }
    }
}