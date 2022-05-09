package com.tenutz.cracknotifier.di.module

import com.tenutz.cracknotifier.data.api.CrackNotifierApi
import com.tenutz.cracknotifier.data.paging.repository.CrackPagingRepository
import com.tenutz.cracknotifier.data.paging.repository.CrackPagingRepositoryImpl
import com.tenutz.cracknotifier.util.mapper.CracksMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    companion object {

        @Singleton
        @Provides
        fun provideNoticePagingRepository(
            crackNotifierApi: CrackNotifierApi,
            mapper: CracksMapper,
        ): CrackPagingRepository {
            return CrackPagingRepositoryImpl(crackNotifierApi, mapper)
        }
    }

}