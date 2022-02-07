package com.psg.lottoapp

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class LottoApp: Application() {
    companion object{
        lateinit var INSTANCE: LottoApp
        fun getContext(): Context = INSTANCE.applicationContext
        fun getGson(): Gson = Gson()
    }

    init {
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@LottoApp)
            modules()
        }
    }

}