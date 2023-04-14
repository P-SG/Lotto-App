package com.psg.domain.usecase

import com.psg.domain.model.Lotto
import com.psg.domain.repository.AppRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetRemoteLottoUseCase(
    private val repository: AppRepository,
    coroutineDispatcher: CoroutineDispatcher
    ): BaseUseCase<Int, Lotto>(coroutineDispatcher) {
    override fun execute(parameter: Int): Flow<Result<Lotto>> {
        return repository.getRemoteLotto(parameter)
    }
}