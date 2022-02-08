package com.psg.lottoapp.data.repository

import com.psg.lottoapp.data.api.LottoAPI
import com.psg.lottoapp.data.db.dao.LottoDao
import com.psg.lottoapp.data.model.LottoEntity
import com.psg.lottoapp.data.model.LottoNum
import retrofit2.Response

class AppRepository constructor(private val dao: LottoDao, private val api: LottoAPI) {

    suspend fun searchLotto(drwNo: Int): Response<LottoNum> = api.getLottoNum(drwNo)

    // Room
    fun getLottoNum() = dao.getLotto()

    suspend fun insertLotto(lottoEntity: LottoEntity) {
        dao.insertLotto(lottoEntity)
    }

    suspend fun updateLotto(lottoEntity: LottoEntity) {
        dao.updateLotto(lottoEntity)
    }

    suspend fun deleteLotto(){
        dao.deleteLotto()
    }

}