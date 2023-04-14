package com.psg.domain.usecase

import com.psg.domain.repository.AppRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class DeleteLottoUseCase(
    private val repository: AppRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Unit, Unit>(coroutineDispatcher) {
    override fun execute(parameter: Unit): Flow<Result<Unit>> {
        return repository.deleteLotto()
    }
}