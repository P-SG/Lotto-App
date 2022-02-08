package com.psg.lottoapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.psg.lottoapp.data.db.dao.LottoDao
import com.psg.lottoapp.data.model.LottoEntity

@Database(entities = [LottoEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun LottoDao(): LottoDao
}