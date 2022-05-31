package com.psg.lottoapp

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LottoApp: Application() {
//    companion object{
//        lateinit var INSTANCE: LottoApp
//        fun getContext(): Context = INSTANCE.applicationContext
//        fun getGson(): Gson = Gson()
//    }
//
//    init {
//        INSTANCE = this
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        INSTANCE = this
////        startKoin {
////            androidLogger(Level.NONE)
////            androidContext(this@LottoApp)
////            modules(listOf(appModule, viewModelModule, repositoryModule))
////        }
//    }

}