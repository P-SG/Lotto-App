package com.psg.lottoapp.module

import com.psg.data.api.LottoAPI
import com.psg.data.repository.remote.RemoteDataSource
import com.psg.data.repository.remote.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Provides
    @Singleton
    fun provideApiService(
    ): com.psg.data.api.LottoAPI {
        return Retrofit.Builder()
            .baseUrl("https://www.dhlottery.co.kr")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(com.psg.data.api.LottoAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteData(api: LottoAPI): RemoteDataSource = RemoteDataSourceImpl(api)
}