package com.psg.lottoapp

import android.app.Application
import com.psg.lottoapp.module.appModules
import com.psg.lottoapp.utils.TimberDebugTree
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class LottoApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@LottoApp)
            modules(appModules)
        }
        if (BuildConfig.DEBUG) {
            Timber.plant(TimberDebugTree())
        }
    }
}