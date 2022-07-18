package com.psg.lottoapp.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.psg.domain.model.Lotto
import com.psg.domain.model.LottoDate
import com.psg.domain.usecase.DeleteLottoUseCase
import com.psg.domain.usecase.GetLocalLottoUseCase
import com.psg.domain.usecase.GetRemoteLottoUseCase
import com.psg.domain.usecase.InsertLottoUseCase
import com.psg.lottoapp.R
import com.psg.lottoapp.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRemoteLottoUseCase: GetRemoteLottoUseCase,
    private val getLocalLottoUseCase: GetLocalLottoUseCase,
    private val insertLottoUseCase: InsertLottoUseCase,
    private val deleteLottoUseCase: DeleteLottoUseCase
    ):BaseViewModel() {

    val lottoDate: LiveData<LottoDate?> get() = _lottoDate
    private val _lottoDate = MutableLiveData<LottoDate?>()

    val lottoNum: LiveData<Lotto?> get() = _lottoNum
    private var _lottoNum = MutableLiveData<Lotto?>()


    fun getRemoteLotto(drwNo:Int){
        var lotto: Lotto? = null
        viewModelScope.launch {
            withContext(Dispatchers.Default){
                getRemoteLottoUseCase(drwNo).collect {
                    lotto = it
                }
            }
            _lottoNum.value = lotto

        }
    }

    fun getLocalLotto(){
        var lottoDate: LottoDate? = null
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                getLocalLottoUseCase().collect {
                    lottoDate = it
                }
            }
            _lottoDate.value = lottoDate
        }
    }


    fun insertLotto(lotto: LottoDate) = CoroutineScope(Dispatchers.IO).launch { insertLottoUseCase(lotto) }

    fun deleteLotto() = CoroutineScope(Dispatchers.IO).launch { deleteLottoUseCase }

    fun parseColor(num: Int) = when(num){
        in 1..10 -> R.color.num1
        in 11..20 -> R.color.num2
        in 21..30 -> R.color.num3
        in 31..40 -> R.color.num4
        in 41..45 -> R.color.num5
        else -> R.color.black
    }

}