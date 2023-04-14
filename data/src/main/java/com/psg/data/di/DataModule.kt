package com.psg.data.di

import androidx.room.Room
import com.psg.data.api.LottoAPI
import com.psg.data.db.AppDatabase
import com.psg.data.repository.AppRepositoryImpl
import com.psg.data.repository.local.LocalDataSource
import com.psg.data.repository.local.LocalDataSourceImpl
import com.psg.data.repository.remote.RemoteDataSource
import com.psg.data.repository.remote.RemoteDataSourceImpl
import com.psg.domain.repository.AppRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val localDataModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "lotto.db"
        ).build()
    }

    single {
        get<AppDatabase>().LottoDao()
    }

    factory<LocalDataSource> {
        LocalDataSourceImpl(dao = get())
    }
}

val remoteDataModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://www.dhlottery.co.kr")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(LottoAPI::class.java)
    }

    factory<RemoteDataSource> {
        RemoteDataSourceImpl(api = get())
    }
}

val repositoryModule = module {
    factory<AppRepository> {
        AppRepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get()
        )
    }
}