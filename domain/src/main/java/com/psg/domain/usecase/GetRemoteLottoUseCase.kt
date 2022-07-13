package com.psg.domain.usecase

import com.psg.domain.repository.AppRepository

class GetRemoteLottoUseCase(private val repository: AppRepository) {
    suspend operator fun invoke(drwNum: Int) = repository.getRemoteLotto(drwNum)
}