package com.psg.domain.usecase

import com.psg.domain.model.Lotto
import com.psg.domain.repository.AppRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class SyncLottoUseCase(
    private val repository: AppRepository,
    coroutineDispatcher: CoroutineDispatcher
): BaseUseCase<Unit, Lotto>(coroutineDispatcher) {
    override fun execute(parameter: Unit): Flow<Result<Lotto>> {
        return repository.syncLotto()
    }
}