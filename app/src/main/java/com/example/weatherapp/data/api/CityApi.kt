package com.example.weatherapp.data.api

import com.example.weatherapp.data.model.CityResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApi {

    @GET("direct?")
    suspend fun getCity(
        @Query("q") city: String,
        @Query("limit") limit: String = "1"
    ): List<CityResponse>

    @GET("reverse?")
    suspend fun getCityByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("limit") limit: String = "1"
    ): List<CityResponse>
}