package com.psg.lottoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LottoEntity (
    @PrimaryKey
    val drwNo: Int,
    val drwNoDate: String,
)