package com.example.weatherapp.data.model

import com.example.weatherapp.domain.model.City
import com.google.gson.annotations.SerializedName

data class CityResponse(
    val name: String,
    @SerializedName("local_names") val localNames: City,
    val lat: Double,
    val lon: Double,
    val country: String
)