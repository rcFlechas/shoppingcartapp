package com.rcflechas.shoppingcartapp.app

import android.app.Application
import com.facebook.stetho.Stetho
import com.rcflechas.shoppingcartapp.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}