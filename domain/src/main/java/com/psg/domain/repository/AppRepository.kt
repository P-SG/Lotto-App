package com.psg.domain.repository

import com.psg.domain.model.Lotto
import com.psg.domain.model.LottoDate
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun getRemoteLotto(drwNum:Int): Flow<Result<Lotto>>
    fun getLocalLotto(): Flow<Result<LottoDate>>
    fun insertLotto(lotto: LottoDate): Flow<Result<Unit>>
    fun updateLotto(lotto: LottoDate): Flow<Result<Unit>>
    fun deleteLotto(): Flow<Result<Unit>>
}