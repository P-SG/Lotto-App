package com.psg.lottoapp.ui.main

import androidx.lifecycle.viewModelScope
import com.psg.domain.model.Lotto
import com.psg.domain.usecase.GetLottoUseCase
import com.psg.domain.usecase.InsertLottoUseCase
import com.psg.lottoapp.ui.base.BaseState
import com.psg.lottoapp.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed class LottoState : BaseState {
    object Loading : LottoState()
    data class Success(val data: Lotto? = null) : LottoState()
    object Error : LottoState()
}

class LottoViewModel(
    private val getLottoUseCase: GetLottoUseCase
) : BaseViewModel<LottoState>() {

    init {
        getLotto()
    }

    override fun setInitialState(): LottoState = LottoState.Loading

    fun getLotto(drwNo: Int? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            getLottoUseCase(drwNo).collect { result ->
                result.onSuccess {
                    reduce { LottoState.Success(data = it) }
                }.onFailure {
                    reduce { LottoState.Error }
                }
            }
        }
    }

}