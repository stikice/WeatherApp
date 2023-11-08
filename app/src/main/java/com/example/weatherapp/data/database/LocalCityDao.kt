package com.example.weatherapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocalCityDao {
    @Query("SELECT * FROM city")
    suspend fun getCities(): List<LocalCityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(city: LocalCityEntity)

    @Query("DELETE FROM city WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM city")
    suspend fun clear()
}