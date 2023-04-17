package com.psg.lottoapp.ui.generate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.psg.lottoapp.R
import com.psg.lottoapp.ui.base.BaseState
import com.psg.lottoapp.ui.base.BaseViewModel
import timber.log.Timber
import kotlin.random.Random

sealed class GenerateNumState: BaseState {
    object Loading: GenerateNumState()
    data class Success(val data: Map<Int, Int>): GenerateNumState()
    object Error: GenerateNumState()
}

class GenerateNumViewModel : BaseViewModel<GenerateNumState>() {

    init {
        generateLotto()
    }

    override fun setInitialState(): GenerateNumState = GenerateNumState.Loading


    fun generateLotto() {
        (1..6).associateWith { Random.nextInt(1, 45) }.also {
            reduce { GenerateNumState.Success(it) }
        }
    }

}