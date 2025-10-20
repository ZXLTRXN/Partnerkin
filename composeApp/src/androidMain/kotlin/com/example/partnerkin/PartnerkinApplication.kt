package com.example.partnerkin

import android.app.Application
import com.example.partnerkin.di.nativeModule
import com.example.partnerkin.di.startDI
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class PartnerkinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startDI(nativeModule) {
            androidLogger() // Koin logger for Android
            androidContext(this@PartnerkinApplication)
        }
    }
}
