package com.example.weatherapp.data.repository

import com.example.weatherapp.data.api.CityApi
import com.example.weatherapp.data.model.CityResponse
import com.example.weatherapp.domain.repository.CityRepository

class CityRepositoryImpl(
    private val cityApi: CityApi
): CityRepository {
    override suspend fun getCity(city: String): List<CityResponse> {
        return cityApi.getCity(city)
    }

    override suspend fun getCityByCoordinates(lat: Double, lon: Double): List<CityResponse> {
        return cityApi.getCityByCoordinates(lat, lon)
    }
}