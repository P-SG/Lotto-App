package com.psg.domain.usecase

import com.psg.domain.model.LottoDate
import com.psg.domain.repository.AppRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class InsertLottoUseCase(
    private val repository: AppRepository,
    coroutineDispatcher: CoroutineDispatcher
    ): BaseUseCase<LottoDate, Unit>(coroutineDispatcher) {
    override fun execute(parameter: LottoDate): Flow<Result<Unit>> {
        return repository.insertLotto(parameter)
    }
}