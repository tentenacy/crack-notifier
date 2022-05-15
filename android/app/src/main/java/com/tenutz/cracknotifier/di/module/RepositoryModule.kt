package com.tenutz.cracknotifier.di.module

import com.tenutz.cracknotifier.data.api.CrackNotifierApi
import com.tenutz.cracknotifier.data.paging.repository.CrackPagingRepository
import com.tenutz.cracknotifier.data.paging.repository.CrackPagingRepositoryImpl
import com.tenutz.cracknotifier.data.repository.UserRepository
import com.tenutz.cracknotifier.data.repository.UserRepositoryImpl
import com.tenutz.cracknotifier.repository.crack.CrackRepository
import com.tenutz.cracknotifier.repository.crack.CrackRepositoryImpl
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
        fun provideCrackPagingRepository(
            crackNotifierApi: CrackNotifierApi,
            mapper: CracksMapper,
        ): CrackPagingRepository {
            return CrackPagingRepositoryImpl(crackNotifierApi, mapper)
        }
    }

    @Singleton
    @Binds
    abstract fun provideCrackRepository(repository: CrackRepositoryImpl): CrackRepository

    @Singleton
    @Binds
    abstract fun provideUserRepository(repository: UserRepositoryImpl): UserRepository

}