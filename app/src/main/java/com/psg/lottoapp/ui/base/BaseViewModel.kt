package com.psg.lottoapp.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

abstract class BaseViewModel:ViewModel() {
    protected val exceptionHandler by lazy {
        CoroutineExceptionHandler { _, t ->
            Timber.e(t)
        }
    }


}