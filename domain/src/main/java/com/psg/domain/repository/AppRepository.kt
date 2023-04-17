package com.psg.domain.repository

import com.psg.domain.model.Lotto
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun getLotto(drwNum: Int?): Flow<Result<Lotto>>

    fun syncLotto(): Flow<Result<Lotto>>
    fun insertLotto(lotto: Lotto): Flow<Result<Unit>>
    fun updateLotto(lotto: Lotto): Flow<Result<Unit>>
    fun deleteLotto(): Flow<Result<Unit>>
}