package com.anama.testrappi

import android.app.Application
import com.anama.testrappi.di.appModule
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.android.startKoin

class App: Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule))
        Fresco.initialize(this)

    }

}