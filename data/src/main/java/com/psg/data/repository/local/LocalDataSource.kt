package com.psg.data.repository.local

import com.psg.data.model.local.LottoEntity

interface LocalDataSource {
    suspend fun getLottoNum(): LottoEntity?

    suspend fun insertLotto(lottoEntity: LottoEntity)

    suspend fun updateLotto(lottoEntity: LottoEntity)

    suspend fun deleteLotto()
}