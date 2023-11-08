package com.example.weatherapp.data.repository

import com.example.weatherapp.data.api.WeatherApi
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.domain.repository.WeatherRepository

class WeatherRepositoryImpl (
    private val weatherApi: WeatherApi
): WeatherRepository {

    override suspend fun getWeatherData(lat: Double, lon: Double, lang: String): WeatherResponse {
        return weatherApi.getWeather(lat, lon, lang)
    }

}