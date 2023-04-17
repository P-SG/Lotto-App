package com.psg.data.mapper

import com.psg.data.model.local.LottoEntity
import com.psg.domain.model.Lotto

fun Lotto.toEntity(): LottoEntity = this.let {
    LottoEntity(
        it.drwNo,
        it.drwNoDate
    )
}