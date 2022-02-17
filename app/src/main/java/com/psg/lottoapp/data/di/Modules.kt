package com.psg.lottoapp.data.di

import androidx.room.Room
import com.psg.lottoapp.data.api.LottoAPI
import com.psg.lottoapp.data.db.AppDatabase
import com.psg.lottoapp.data.repository.AppRepository
import com.psg.lottoapp.view.generate.GeneNumViewModel
import com.psg.lottoapp.view.main.MainViewModel
import com.psg.lottoapp.view.qrscan.QRScanViewModel
import com.psg.lottoapp.view.splash.SplashViewModel
import get
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

    val appModule = module {
        single {
            Retrofit.Builder()
                .baseUrl("https://www.dhlottery.co.kr")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LottoAPI::class.java)
        }
        single {
            Room.databaseBuilder(
                androidApplication(),
                AppDatabase::class.java,
                "lotto-app.db")
                .build()
        }
        single { get<AppDatabase>().LottoDao() }
    }

    val viewModelModule = module {
        viewModel { MainViewModel(get()) }
        viewModel { GeneNumViewModel() }
        viewModel { QRScanViewModel(get()) }
        viewModel { SplashViewModel(get()) }
    }

    val repositoryModule = module {
        single { AppRepository(get(),get()) }
    }
