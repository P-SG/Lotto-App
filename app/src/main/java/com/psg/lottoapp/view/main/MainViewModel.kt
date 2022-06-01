package com.psg.lottoapp.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.psg.lottoapp.R
import com.psg.lottoapp.data.model.Lotto
import com.psg.lottoapp.data.model.LottoEntity
import com.psg.lottoapp.data.model.LottoResponse
import com.psg.lottoapp.data.repository.AppRepository
import com.psg.lottoapp.util.AppLogger
import com.psg.lottoapp.util.responseToLotto
import com.psg.lottoapp.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: AppRepository):BaseViewModel() {

    val lottoEntity: LiveData<LottoEntity> get() = repository.getLottoNum()

    val lottoNum: LiveData<Lotto> get() = _lottoNum
    private var _lottoNum = MutableLiveData<Lotto>()


    fun searchLotto(drwNo:Int){
        var res = LottoResponse("","",0,0,0,0,0,0,0,0,0,0,0,0)
        viewModelScope.launch {
            withContext(Dispatchers.Default){
                val body = repository.searchLotto(drwNo).body()
                val code = repository.searchLotto(drwNo).code()

                if (code == 200 && body != null){
                    AppLogger.println("바디는?$body")
                    res = body
                }
            }
            _lottoNum.value = responseToLotto(res)

        }
    }


    fun insertLotto(lottoEntity: LottoEntity) = CoroutineScope(Dispatchers.IO).launch { repository.insertLotto(lottoEntity) }

    fun deleteLotto() = CoroutineScope(Dispatchers.IO).launch { repository.deleteLotto() }

    fun parseColor(num: Int) = when(num){
        in 1..10 -> R.color.num1
        in 11..20 -> R.color.num2
        in 21..30 -> R.color.num3
        in 31..40 -> R.color.num4
        in 41..45 -> R.color.num5
        else -> R.color.black
    }

}