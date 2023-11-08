package com.example.weatherapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocalCity(
    val id: Int? = null,
    var name: String,
    var lat: Double,
    var lon: Double
): Parcelable
