package com.psg.lottoapp.view.qrscan

import com.psg.lottoapp.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QRScanViewModel @Inject constructor(private val repository: com.psg.data.repository.AppRepositoryImpl): BaseViewModel() {
}