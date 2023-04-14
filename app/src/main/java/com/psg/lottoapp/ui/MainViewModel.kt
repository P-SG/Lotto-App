package com.psg.lottoapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.lifecycle.viewModelScope
import com.psg.domain.model.LottoDate
import com.psg.domain.usecase.DeleteLottoUseCase
import com.psg.domain.usecase.GetLocalLottoUseCase
import com.psg.domain.usecase.GetRemoteLottoUseCase
import com.psg.domain.usecase.InsertLottoUseCase
import com.psg.lottoapp.ui.base.BaseViewModel
import com.psg.lottoapp.ui.main.LottoFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.text.SimpleDateFormat
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
    private val deleteLottoUseCase: DeleteLottoUseCase
) : BaseViewModel() {

    private val _appStateFlow: MutableStateFlow<AppState> = MutableStateFlow(AppState.Loading)
    private val appStateFlow get() = _appStateFlow.asStateFlow()

    //    fun updateLotto(lottoEntity: com.psg.data.model.local.LottoEntity) = CoroutineScope(Dispatchers.IO).launch { repository.updateLotto(lottoEntity) }
    fun insertLotto(lottoDate: LottoDate) =
        CoroutineScope(Dispatchers.IO).launch { insertLottoUseCase(lottoDate) }

    fun deleteLotto() = CoroutineScope(Dispatchers.IO).launch { deleteLottoUseCase() }

    init {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            getLocalLottoUseCase(Unit)
                .collect { result ->
                    result.onSuccess {
                        getRemoteLottoUseCase(it.drwNo + it.drwNoDate.toCalDate())

                    }.onFailure {
                        _appStateFlow.emit(AppState.Error)
                    }
                }
        }
    }

    suspend fun getRemoteLotto(drwNo: Int) = withContext(Dispatchers.IO + exceptionHandler) {
        getRemoteLottoUseCase(drwNo)
            .collect { result ->
                result.onSuccess {
                    return@collect it
                }
            }
    }

    fun getLocalLotto() {
        var lottoDate: LottoDate? = null
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getLocalLottoUseCase().collect {
                    lottoDate = it
                }
            }
            _lottoDate.value = lottoDate
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun String.toCalDate() = runCatching {
        val sdf = SimpleDateFormat("yyyy-MM-dd 00:00:00")
        val date = sdf.parse("$this 00:00:00")
        val today = Calendar.getInstance()
        val calDate = (today.time.time - date.time) / (60 * 60 * 24 * 1000)
        Timber.d("날짜차이:${(calDate / 7)}주지남")
        return@runCatching (calDate / 7).toInt()
    }.getOrThrow()

    private fun checkNewDrwNo() {

        viewModel.lottoDate.observe(this) {
            if (it != null) {
                AppLogger.println("저장된 회차:${it.drwNo}")
                if (calDate > 7) {
                    AppLogger.println("최신회차가 아님")
                    AppLogger.println("날짜차이:${(calDate / 7)}주지남}")
                    viewModel.getRemoteLotto((it.drwNo + (calDate / 7).toInt()))
                    viewModel.lottoNum.observe(this) { num ->
                        if (num != null) {
                            viewModel.deleteLotto()
                            viewModel.insertLotto(
                                LottoDate(
                                    num.drwNo,
                                    num.drwNoDate
                                )
                            )
                        }
                    }

                } else {
                    AppLogger.println("최신회차")
                    startActivity(intent)
                    finish()
                }
            } else {
                AppLogger.println("앱이 처음 실행")
                AppLogger.println("저장된 회차가 없음")
                val sf = SimpleDateFormat("yyyy-MM-dd 00:00:00")
                val date = sf.parse("2022-01-29 00:00:00")
                val today = Calendar.getInstance()
                val calDate = (today.time.time - date.time) / (60 * 60 * 24 * 1000)
                AppLogger.println("날짜차이:${(calDate / 7)}주지남")

                viewModel.getRemoteLotto(1000 + (calDate / 7).toInt())
                viewModel.lottoNum.observe(this) { num ->
                    if (num != null) {
                        viewModel.insertLotto(
                            LottoDate(
                                num.drwNo,
                                num.drwNoDate
                            )
                        )
                        startActivity(intent)
                        finish()
                    }
                }

            }
        }
    }

}