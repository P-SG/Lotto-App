package com.psg.data.mapper

import com.psg.data.model.local.LottoEntity
import com.psg.data.model.remote.LottoResponse
import com.psg.domain.model.Lotto
import com.psg.domain.model.LottoDate

fun LottoDate.toEntity(): LottoEntity = this.let {
    LottoEntity(
        it.drwNo,
        it.drwNoDate
    )
}