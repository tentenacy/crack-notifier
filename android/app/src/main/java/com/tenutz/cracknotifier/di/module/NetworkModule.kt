package com.tenutz.cracknotifier.di.module

import com.google.gson.GsonBuilder
import com.tenutz.cracknotifier.BuildConfig
import com.tenutz.cracknotifier.data.api.CrackNotifierApi
import com.tenutz.cracknotifier.network.interceptor.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Singleton
    @Provides
    fun provideTokenInterceptor(): TokenInterceptor {
        return TokenInterceptor()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        tokenInterceptor: TokenInterceptor,
    ): Retrofit.Builder {

        val client = OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
    }

    @Singleton
    @Provides
    fun provideCrackNotifierApi(retrofitBuilder: Retrofit.Builder): CrackNotifierApi {
        return CrackNotifierApi.create(retrofitBuilder)
    }

}