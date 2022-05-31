package com.psg.lottoapp.view.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.psg.lottoapp.data.model.Lotto
import com.psg.lottoapp.data.model.LottoEntity
import com.psg.lottoapp.data.model.LottoResponse
import com.psg.lottoapp.data.repository.AppRepository
import com.psg.lottoapp.util.responseToLotto
import com.psg.lottoapp.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository: AppRepository): BaseViewModel() {
    val lottoEntity: LiveData<LottoEntity> get() = repository.getLottoNum()
    val lottoNum: LiveData<Lotto> get() = _lottoNum
    private var _lottoNum = MutableLiveData<Lotto>()

    fun updateLotto(lottoEntity: LottoEntity) = CoroutineScope(Dispatchers.IO).launch { repository.updateLotto(lottoEntity) }
    fun insertLotto(lottoEntity: LottoEntity) = CoroutineScope(Dispatchers.IO).launch { repository.insertLotto(lottoEntity) }
    fun deleteLotto() = CoroutineScope(Dispatchers.IO).launch { repository.deleteLotto() }

    fun searchLotto(drwNo:Int){
        var res = LottoResponse("","",0,0,0,0,0,0,0,0,0,0,0,0)
        viewModelScope.launch {
            withContext(Dispatchers.Default){
                val body = repository.searchLotto(drwNo).body()
                val code = repository.searchLotto(drwNo).code()

                if (code == 200 && body != null){
                    println("바디는?$body")
                    res = body
                }
            }
            _lottoNum.value = responseToLotto(res)

        }
    }

}