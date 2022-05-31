package com.psg.lottoapp.view.qrscan

import com.psg.lottoapp.data.repository.AppRepository
import com.psg.lottoapp.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QRScanViewModel @Inject constructor(private val repository: AppRepository): BaseViewModel() {
}