package com.psg.data.model.remote

import com.psg.domain.model.Lotto


data class LottoResponse(
    val returnValue: String, // 요청결과
    val drwNoDate: String, // 날짜
    val totSellamnt: Long, // 총상금
    val firstWinamnt: Long, // 1등 상금
    val firstPrzwnerCo: Int, // 1등 당첨인원
    val firstAccumamnt: Long,
    val drwtNo1: Int,
    val drwtNo2: Int,
    val drwtNo3: Int,
    val drwtNo4: Int,
    val drwtNo5: Int,
    val drwtNo6: Int,
    val bnusNo: Int,
    val drwNo: Int
)

fun LottoResponse.toDomain(): Lotto = Lotto(
    drwNoDate,
    drwtNo1,
    drwtNo2,
    drwtNo3,
    drwtNo4,
    drwtNo5,
    drwtNo6,
    bnusNo,
    drwNo
)
