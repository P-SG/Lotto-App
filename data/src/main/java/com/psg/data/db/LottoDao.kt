package com.psg.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.psg.data.model.local.LottoEntity

@Dao
interface LottoDao {
    @Query("SELECT * FROM LottoEntity")
    fun getLotto(): LottoEntity

    @Insert
    suspend fun insertLotto(lottoEntity: LottoEntity)

    @Update
    suspend fun updateLotto(lottoEntity: LottoEntity)

    @Query("DELETE FROM LottoEntity")
    suspend fun deleteLotto()
}