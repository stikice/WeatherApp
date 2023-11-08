package com.example.weatherapp.di

import com.example.weatherapp.data.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val databaseModule = module {
    single {
        AppDatabase.newInstance(androidContext())
    }

    single {
        get<AppDatabase>().getLocalCityDao()
    }
}