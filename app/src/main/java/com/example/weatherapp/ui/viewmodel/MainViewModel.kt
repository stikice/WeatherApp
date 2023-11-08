package com.example.weatherapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.CityResponse
import com.example.weatherapp.domain.model.LocalCity
import com.example.weatherapp.domain.repository.CityRepository
import com.example.weatherapp.domain.repository.LocalCityRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class MainViewModel(
    private val localCityRepository: LocalCityRepository,
    private val cityRepository: CityRepository
) : ViewModel() {

    var currentItem = 0

    private val cityMutableLiveData = MutableLiveData<CityResponse?>()

    val cityLiveData: LiveData<CityResponse?>
        get() = cityMutableLiveData

    private val citiesMutableLiveData = MutableLiveData<List<LocalCity>?>()

    val citiesLiveData: LiveData<List<LocalCity>?>
        get() = citiesMutableLiveData

    fun addCity(city: LocalCity) {
        viewModelScope.launch {
            localCityRepository.add(city)
        }
    }

    fun getCities() {
        viewModelScope.launch {
            val response = localCityRepository.getCities()
            citiesMutableLiveData.value = response
        }
    }

    fun deleteCity(city: LocalCity) {
        viewModelScope.launch {
            city.id?.let { localCityRepository.delete(it) }
            getCities()
        }
    }

    fun checkCity(city: String) {
        viewModelScope.launch {
            val response = try {
                val result = withTimeout(3000) {
                    cityRepository.getCity(city)
                }
                result
            } catch (ex: Exception) {
                null
            }
            cityMutableLiveData.value = if (!response.isNullOrEmpty()) {response.first()} else null
        }
    }
}