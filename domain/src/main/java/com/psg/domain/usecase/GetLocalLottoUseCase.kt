package com.psg.domain.usecase

import com.psg.domain.repository.AppRepository

class GetLocalLottoUseCase(private val repository: AppRepository) {
    suspend operator fun invoke() = repository.getLocalLotto()

}