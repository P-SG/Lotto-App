package com.psg.lottoapp.ui

import android.annotation.SuppressLint
import androidx.lifecycle.viewModelScope
import com.psg.domain.model.LottoDate
import com.psg.domain.model.toDate
import com.psg.domain.usecase.*
import com.psg.lottoapp.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

sealed class AppState {
    object Loading : AppState()

    object Success : AppState()

    object Error : AppState()
}

class MainViewModel(
    private val getRemoteLottoUseCase: GetRemoteLottoUseCase,
    private val getLocalLottoUseCase: GetLocalLottoUseCase,
    private val insertLottoUseCase: InsertLottoUseCase,
    private val deleteLottoUseCase: DeleteLottoUseCase,
    private val updateLottoUseCase: UpdateLottoUseCase
) : BaseViewModel() {

    private val _appStateFlow: MutableStateFlow<AppState> = MutableStateFlow(AppState.Loading)
    val appStateFlow get() = _appStateFlow.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val localData = getLocalLottoUseCase(Unit).first().getOrNull()
            localData?.also { local ->
                if (checkLocalData(local)) {
                    getRemoteLottoUseCase(local.drwNo + local.drwNoDate.toCalDate()).collect { remoteResult ->
                        remoteResult.onSuccess {
                            updateLottoUseCase(it.toDate())
                            _appStateFlow.emit(AppState.Success)
                        }.onFailure {
                            _appStateFlow.emit(AppState.Error)
                        }
                    }
                }
            } ?: run {
                getRemoteLottoUseCase(1000 + LocalDate.now().toCalDate()).collect { remoteResult ->
                    remoteResult.onSuccess {
                        insertLottoUseCase(it.toDate())
                        _appStateFlow.emit(AppState.Success)
                    }.onFailure {
                        _appStateFlow.emit(AppState.Error)
                    }
                }
            }
        }
    }

    private fun checkLocalData(date: LottoDate) = date.drwNoDate.toCalDate() > 7

    @SuppressLint("SimpleDateFormat")
    private fun String.toCalDate() = runCatching {
        val sdf = SimpleDateFormat("yyyy-MM-dd 00:00:00")
        val date = sdf.parse("$this 00:00:00")
        val today = Calendar.getInstance()
        val calDate = (today.time.time - date.time) / (60 * 60 * 24 * 1000)
        Timber.d("날짜차이:${(calDate / 7)}주지남")
        return@runCatching (calDate / 7).toInt()
    }.getOrThrow()

    @SuppressLint("SimpleDateFormat")
    private fun LocalDate.toCalDate() = runCatching {
        val sdf = SimpleDateFormat("yyyy-MM-dd 00:00:00")
        val date = sdf.parse("$this 00:00:00")
        val today = Calendar.getInstance()
        val calDate = (today.time.time - date.time) / (60 * 60 * 24 * 1000)
        Timber.d("날짜차이:${(calDate / 7)}주지남")
        return@runCatching (calDate / 7).toInt()
    }.getOrThrow()

}