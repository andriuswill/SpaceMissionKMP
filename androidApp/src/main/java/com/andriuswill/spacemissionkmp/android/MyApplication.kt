package com.andriuswill.spacemissionkmp.android

import android.app.Application
import com.andriuswill.spacemissionkmp.android.di.androidModule
import com.andriuswill.spacemissionkmp.di.appModule
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                androidModule() + appModule()
            )
        }
    }
}
