package com.example.weatherapp.data.database

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "city",
    indices = [Index(value = ["name"], unique = true)]
)
data class LocalCityEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var name: String,
    var lat: Double,
    var lon: Double
) {
    constructor(name: String, lat: Double, lon: Double) : this(0, name, lat, lon)
}
