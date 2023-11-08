package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.di.appModule
import com.example.weatherapp.di.databaseModule
import com.example.weatherapp.di.networkModule
import com.example.weatherapp.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        LocaleManager.init(this)
    }

    private fun initKoin() {
        startKoin {
            //androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)
            //androidFileProperties()
            modules(provideModules())
        }
    }

    private fun provideModules() = listOf(appModule, networkModule, databaseModule, repositoryModule)
}