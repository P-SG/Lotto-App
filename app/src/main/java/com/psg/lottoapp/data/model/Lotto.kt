package com.psg.lottoapp.data.model

import com.psg.lottoapp.R

data class Lotto(
    val drwNoDate: String,
    val drwtNo1: Int,
    val drwtNo2: Int,
    val drwtNo3: Int,
    val drwtNo4: Int,
    val drwtNo5: Int,
    val drwtNo6: Int,
    val bnusNo: Int,
    val drwNo: Int
){





    fun getNum(num:Int) = num.toString()
}