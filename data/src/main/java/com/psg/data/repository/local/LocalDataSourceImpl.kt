package com.psg.data.repository.local

import com.psg.data.db.LottoDao
import com.psg.data.model.local.LottoEntity

class LocalDataSourceImpl(private val dao: LottoDao):LocalDataSource {
    override suspend fun getLottoNum() = dao.getLotto()

    override suspend fun insertLotto(lottoEntity: LottoEntity) {
        dao.insertLotto(lottoEntity)
    }

    override suspend fun updateLotto(lottoEntity: LottoEntity) {
        dao.updateLotto(lottoEntity)
    }

    override suspend fun deleteLotto(){
        dao.deleteLotto()
    }
}