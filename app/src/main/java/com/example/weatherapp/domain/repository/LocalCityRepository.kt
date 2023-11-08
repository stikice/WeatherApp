package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.model.LocalCity

interface LocalCityRepository {

    suspend fun add(city: LocalCity)

    suspend fun getCities(): List<LocalCity>

    suspend fun delete(id: Int)
}