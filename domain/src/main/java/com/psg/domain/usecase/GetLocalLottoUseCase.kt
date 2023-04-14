package com.psg.domain.usecase

import com.psg.domain.model.LottoDate
import com.psg.domain.repository.AppRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetLocalLottoUseCase(
    private val repository: AppRepository, coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Unit, LottoDate>(coroutineDispatcher) {
    override fun execute(parameter: Unit): Flow<Result<LottoDate>> {
        return repository.getLocalLotto()
    }

}