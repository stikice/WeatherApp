package com.example.weatherapp.di

import com.example.weatherapp.ui.viewmodel.MainViewModel
import com.example.weatherapp.ui.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(localCityRepository = get(), cityRepository = get()) }
    viewModel { WeatherViewModel(weatherRepository = get(), cityRepository = get()) }
}