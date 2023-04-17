package com.psg.lottoapp.module

import com.psg.data.di.localDataModule
import com.psg.data.di.remoteDataModule
import com.psg.data.di.repositoryModule
import com.psg.data.di.useCaseModule
import com.psg.lottoapp.ui.MainViewModel
import com.psg.lottoapp.ui.generate.GenerateNumViewModel
import com.psg.lottoapp.ui.main.LottoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(
            get(),
        )
    }
    viewModel {
        LottoViewModel(
            get(),
            get(),
        )
    }

    viewModel {
        GenerateNumViewModel(
        )
    }
}


val appModules = listOf(
    viewModelModule,
    remoteDataModule,
    localDataModule,
    repositoryModule,
    useCaseModule
)




