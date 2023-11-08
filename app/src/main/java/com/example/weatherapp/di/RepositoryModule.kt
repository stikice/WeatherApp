package com.example.weatherapp.di

import com.example.weatherapp.domain.repository.LocalCityRepository
import com.example.weatherapp.domain.repository.CityRepository
import com.example.weatherapp.data.repository.CityRepositoryImpl
import com.example.weatherapp.data.repository.LocalCityRepositoryImpl
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.domain.repository.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<LocalCityRepository> { LocalCityRepositoryImpl(cityDao = get()) }
    single<CityRepository> { CityRepositoryImpl(cityApi = get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(weatherApi = get()) }
}