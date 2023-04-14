package com.psg.data.di

import com.psg.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module


val useCaseModule = module {
    single(named("ioDispatcher")) {
        Dispatchers.IO
    }
    factory {
        GetLocalLottoUseCase(
            repository = get(),
            coroutineDispatcher = get(named("ioDispatcher"))
        )
    }
    factory {
        GetRemoteLottoUseCase(
            repository = get(),
            coroutineDispatcher = get(named("ioDispatcher"))
        )
    }
    factory {
        InsertLottoUseCase(
            repository = get(),
            coroutineDispatcher = get(named("ioDispatcher"))
        )
    }
    factory {
        DeleteLottoUseCase(
            repository = get(),
            coroutineDispatcher = get(named("ioDispatcher"))
        )
    }
    factory {
        UpdateLottoUseCase(
            repository = get(),
            coroutineDispatcher = get(named("ioDispatcher"))
        )
    }
}