package com.psg.domain.repository

import com.psg.domain.model.Lotto
import com.psg.domain.model.LottoDate
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    suspend fun getRemoteLotto(drwNum:Int):Flow<Lotto>
    suspend fun getLocalLotto():Flow<LottoDate>
    suspend fun insertLotto(lotto: LottoDate)
    suspend fun updateLotto(lotto: LottoDate)
    suspend fun deleteLotto()
}