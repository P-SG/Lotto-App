package com.psg.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.psg.data.model.local.LottoEntity

@Database(entities = [LottoEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun LottoDao(): LottoDao
}