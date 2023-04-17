package com.psg.lottoapp.ui

import androidx.lifecycle.viewModelScope
import com.psg.domain.usecase.SyncLottoUseCase
import com.psg.lottoapp.ui.base.BaseState
import com.psg.lottoapp.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed class AppState: BaseState {
    object Loading : AppState()

    object Success : AppState()

    object Error : AppState()
}

class MainViewModel(
    private val syncLottoUseCase: SyncLottoUseCase
) : BaseViewModel<AppState>() {

    override fun setInitialState(): AppState = AppState.Loading

    init {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            syncLottoUseCase(Unit)
                .collect { result ->
                    result.onSuccess {
                        reduce { AppState.Success }
                    }.onFailure {
                        reduce { AppState.Error }
                    }
                }
        }
    }

}