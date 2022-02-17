package com.psg.lottoapp.data.model

import com.psg.lottoapp.R

data class LottoNum(
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
) {

     fun numColor(num:Int) =
        when(num){
            in 0..10 -> R.color.num1
            in 11..20 -> R.color.num2
            in 21..30 -> R.color.num3
            in 31..40 -> R.color.num4
            in 41..45 -> R.color.num5
            else -> R.color.black
        }

}
