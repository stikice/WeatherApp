package com.example.weatherapp.ui

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.makeStaticImageUrl(): String {
    return "https://openweathermap.org/img/wn/".plus(this).plus("@2x.png")
}

fun Int.makeTemperature(): String {
    return this.toString().plus("°")
}

fun Double.makePercentage(): String {
    return (this*100).toInt().toString().plus("%")
}

fun Long.convertToHour(): String {
    val sdf = SimpleDateFormat("Ha", Locale.getDefault())
    val date = Date(this * 1000)
    return sdf.format(date)
}

fun Long.convertToDay(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = Date(this * 1000)
    return sdf.format(date)
}

fun Long.convertToDayOfWeek(): String {
    val sdf = SimpleDateFormat("EEE", Locale.getDefault())
    val date = Date(this * 1000)
    return sdf.format(date)
}