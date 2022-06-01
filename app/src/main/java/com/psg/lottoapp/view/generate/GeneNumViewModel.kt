package com.psg.lottoapp.view.generate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.psg.lottoapp.R
import com.psg.lottoapp.data.repository.AppRepository
import com.psg.lottoapp.util.AppLogger
import com.psg.lottoapp.view.base.BaseViewModel
import kotlin.random.Random

class GeneNumViewModel() : BaseViewModel() {
    val lottoNum: LiveData<Map<Int,Int>> get() = _lottoNum
    private var _lottoNum = MutableLiveData<Map<Int,Int>>()


    init {
        geneLottoNum()
    }

    fun geneLottoNum(){
        AppLogger.println("vm.setvalue")
        _lottoNum.value = generateLotto()
    }


    private fun generateLotto():Map<Int,Int> {
        val rd = Random
        val map = HashMap<Int,Int>()

        for (i in 1..6) {
            map[i] = rd.nextInt(1,45)
        }

        return map
    }


     fun numColor(num:Int):Int{
         AppLogger.println("numColor $num")
         return when (num) {
             in 0..10 -> R.color.num1
             in 11..20 -> R.color.num2
             in 21..30 -> R.color.num3
             in 31..40 -> R.color.num4
             in 41..45 -> R.color.num5
             else -> R.color.black
         }
     }



}