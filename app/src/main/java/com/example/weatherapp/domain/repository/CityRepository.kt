package com.example.weatherapp.domain.repository

import com.example.weatherapp.data.model.CityResponse

interface CityRepository {
    suspend fun getCity(city: String): List<CityResponse>?
    suspend fun getCityByCoordinates(lat: Double, lon: Double): List<CityResponse>?
}