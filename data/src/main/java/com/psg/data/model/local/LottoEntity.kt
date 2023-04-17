package com.psg.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LottoEntity (
    @PrimaryKey
    val drwNo: Int,
    val drwNoDate: String,
)