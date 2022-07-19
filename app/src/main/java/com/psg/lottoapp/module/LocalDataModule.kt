package com.psg.lottoapp.module

import android.content.Context
import androidx.room.Room
import com.psg.data.db.AppDatabase
import com.psg.data.db.LottoDao
import com.psg.data.repository.local.LocalDataSource
import com.psg.data.repository.local.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "lol-app.db"
        )
            .build()

    @Provides
    @Singleton
    fun provideLolDao(database: AppDatabase): LottoDao = database.LottoDao()

    @Provides
    @Singleton
    fun provideLolLocalData(dao: LottoDao): LocalDataSource = LocalDataSourceImpl(dao)
}