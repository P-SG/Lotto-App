package com.psg.domain.usecase

import com.psg.domain.model.LottoDate
import com.psg.domain.repository.AppRepository

class UpdateLottoUseCase(private val repository: AppRepository) {
    suspend operator fun invoke(lotto: LottoDate){
        repository.updateLotto(lotto)
    }
}