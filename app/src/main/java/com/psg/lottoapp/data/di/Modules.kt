package com.psg.lottoapp.data.di

import android.content.Context
import androidx.room.Room
import com.psg.lottoapp.data.api.LottoAPI
import com.psg.lottoapp.data.db.AppDatabase
import com.psg.lottoapp.data.db.dao.LottoDao
import com.psg.lottoapp.data.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Modules {
    @Provides
    @Singleton
    fun provideApiService(
    ): LottoAPI {
        return Retrofit.Builder()
            .baseUrl("https://www.dhlottery.co.kr")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LottoAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "lotto-app.db"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideLottoDao(database: AppDatabase): LottoDao {
        return database.LottoDao()
    }

    @Provides
    @Singleton
    fun provideRepository(dao: LottoDao, api: LottoAPI): AppRepository {
        return AppRepository(dao, api)
    }
}

//    val appModule = module {
//        single {
//            Retrofit.Builder()
//                .baseUrl("https://www.dhlottery.co.kr")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(LottoAPI::class.java)
//        }
//        single {
//            Room.databaseBuilder(
//                androidApplication(),
//                AppDatabase::class.java,
//                "lotto-app.db")
//                .build()
//        }
//        single { get<AppDatabase>().LottoDao() }
//    }
//
//    val viewModelModule = module {
//        viewModel { MainViewModel(get()) }
//        viewModel { GeneNumViewModel() }
//        viewModel { QRScanViewModel(get()) }
//        viewModel { SplashViewModel(get()) }
//    }
//
//    val repositoryModule = module {
//        single { AppRepository(get(),get()) }
//    }
