package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<WeatherForecast>,
)

data class WeatherForecast(
    val dt: Long,
    val main: WeatherTemperature,
    val weather: List<WeatherDescription>,
    val pop: Double,
    val sys: WeatherDayNight,
    @SerializedName("dt_txt") val dtTxt: String
)

data class WeatherTemperature(
    val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double,
    val pressure: Double,
    val seaLevel: Double,
    @SerializedName("grnd_level") val grndLevel: Double,
    val humidity: Double,
    @SerializedName("temp_kf") val tempKf: Double,
)

data class WeatherDescription(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class WeatherDayNight(
    val pod: String
)