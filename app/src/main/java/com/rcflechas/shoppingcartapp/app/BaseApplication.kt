package com.rcflechas.shoppingcartapp.app

import android.app.Application
import com.facebook.stetho.Stetho
import com.rcflechas.shoppingcartapp.BuildConfig
import com.rcflechas.shoppingcartapp.app.di.applicationModule
import com.rcflechas.shoppingcartapp.app.di.retrofitModule
import com.rcflechas.shoppingcartapp.app.di.roomModule
import com.rcflechas.shoppingcartapp.app.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }

        startKoin {
            androidContext(this@BaseApplication)
            androidLogger(Level.NONE)
            modules(
                listOf(
                    applicationModule,
                    retrofitModule,
                    roomModule,
                    viewModelModule
                )
            )
        }
    }
}