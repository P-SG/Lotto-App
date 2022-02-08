package com.psg.lottoapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.psg.lottoapp.data.model.LottoEntity

@Dao
interface LottoDao {
    @Query("SELECT * FROM LottoEntity")
    fun getLotto(): LiveData<LottoEntity>

    @Insert
    suspend fun insertLotto(lottoEntity: LottoEntity)

    @Update
    suspend fun updateLotto(lottoEntity: LottoEntity)

    @Query("DELETE FROM LottoEntity")
    suspend fun deleteLotto()
}