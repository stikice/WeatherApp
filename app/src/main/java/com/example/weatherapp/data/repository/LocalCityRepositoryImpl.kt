package com.example.weatherapp.data.repository

import com.example.weatherapp.domain.model.LocalCity
import com.example.weatherapp.data.database.LocalCityDao
import com.example.weatherapp.data.database.LocalCityEntity
import com.example.weatherapp.domain.repository.LocalCityRepository

class LocalCityRepositoryImpl(
    private val cityDao: LocalCityDao
): LocalCityRepository {

    override suspend fun add(city: LocalCity) {
        cityDao.insert(city.toEntity())
    }

    override suspend fun getCities(): List<LocalCity> {
        return cityDao
            .getCities()
            .map { it.toModel() }
    }

    override suspend fun delete(id: Int) {
        cityDao.delete(id)
    }
}

fun LocalCityEntity.toModel(): LocalCity {
    return LocalCity(
        id = id,
        name = name,
        lat = lat,
        lon = lon
    )
}

fun LocalCity.toEntity(): LocalCityEntity {
    return LocalCityEntity(
        name = name,
        lat = lat,
        lon = lon
    )
}