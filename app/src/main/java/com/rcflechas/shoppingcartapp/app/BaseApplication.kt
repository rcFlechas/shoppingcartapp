package com.rcflechas.shoppingcartapp.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            androidLogger(Level.NONE)
            modules(
                /*listOf(
                    applicationModule,
                    networkModule,
                    roomModule,
                    viewModelModule
                )*/
            )
        }
    }
}