package com.psg.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.psg.domain.model.LottoDate

@Entity
data class LottoEntity (
    @PrimaryKey
    val drwNo: Int,
    val drwNoDate: String,
)
fun LottoEntity.toDomain() = LottoDate(
    drwNo,
    drwNoDate
)