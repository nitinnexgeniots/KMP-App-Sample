package com.jetbrains.kmpapp

import android.app.Application
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.jetbrains.kmpapp.di.initKoin

class MainApplication : Application() {
    companion object {

        lateinit  var appContext: Context

    }


    override fun onCreate() {
        super.onCreate()
        MainApplication.appContext = applicationContext
        initKoin()
    }
}
