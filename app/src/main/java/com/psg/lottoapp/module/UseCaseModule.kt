package com.psg.lottoapp.module

import com.psg.domain.repository.AppRepository
import com.psg.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetRemoteUseCase(repository: AppRepository) = GetRemoteLottoUseCase(repository)
    @Provides
    @Singleton
    fun provideGetLocalUseCase(repository: AppRepository) = GetLocalLottoUseCase(repository)
    @Provides
    @Singleton
    fun provideInsertUseCase(repository: AppRepository) = InsertLottoUseCase(repository)
    @Provides
    @Singleton
    fun provideUpdateUseCase(repository: AppRepository) = UpdateLottoUseCase(repository)
    @Provides
    @Singleton
    fun provideDeleteUseCase(repository: AppRepository) = DeleteLottoUseCase(repository)
}