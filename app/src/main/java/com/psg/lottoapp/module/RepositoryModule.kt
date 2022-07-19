package com.psg.lottoapp.module

import com.psg.data.repository.AppRepositoryImpl
import com.psg.data.repository.local.LocalDataSource
import com.psg.data.repository.remote.RemoteDataSource
import com.psg.domain.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAppRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): AppRepository = AppRepositoryImpl(localDataSource, remoteDataSource)

}