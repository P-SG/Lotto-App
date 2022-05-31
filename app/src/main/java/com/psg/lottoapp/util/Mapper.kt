package com.psg.lottoapp.util

import com.psg.lottoapp.data.model.Lotto
import com.psg.lottoapp.data.model.LottoResponse

fun responseToLotto(res:LottoResponse):Lotto = res.let {
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