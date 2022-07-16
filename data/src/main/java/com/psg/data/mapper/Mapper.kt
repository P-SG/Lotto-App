package com.psg.data.mapper

import com.psg.data.model.local.LottoEntity
import com.psg.data.model.remote.LottoResponse
import com.psg.domain.model.Lotto
import com.psg.domain.model.LottoDate


fun responseToDomain(res: LottoResponse): Lotto = res.let {
    Lotto(
        it.drwNoDate,
        it.drwtNo1,
        it.drwtNo2,
        it.drwtNo3,
        it.drwtNo4,
        it.drwtNo5,
        it.drwtNo6,
        it.bnusNo,
        it.drwNo
    )
}

fun domainToEntity(domain: LottoDate): LottoEntity = domain.let {
    LottoEntity(
        it.drwNo,
        it.drwNoDate
    )
}