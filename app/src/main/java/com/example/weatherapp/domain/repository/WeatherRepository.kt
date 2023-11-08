package com.example.weatherapp.domain.repository

import com.example.weatherapp.data.model.WeatherResponse

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, lon: Double, lang: String): WeatherResponse?
}