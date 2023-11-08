package com.example.weatherapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.domain.model.City
import com.example.weatherapp.domain.repository.CityRepository
import com.example.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val cityRepository: CityRepository
) : ViewModel() {


    private val cityNameMutableLiveData = MutableLiveData<City?>()

    val cityNameLiveData: LiveData<City?>
        get() = cityNameMutableLiveData

    private val weatherMutableLiveData = MutableLiveData<WeatherResponse?>()

    val weatherLiveData: LiveData<WeatherResponse?>
        get() = weatherMutableLiveData

    fun getName(lat: Double, lon: Double) {
        viewModelScope.launch {
            val response = cityRepository.getCityByCoordinates(lat, lon)
            if (response != null) {
                cityNameMutableLiveData.value = response.first().localNames
            }
        }
    }

    fun getWeather(lat: Double, lon: Double, lang: String) {
        viewModelScope.launch {
            val response = weatherRepository.getWeatherData(lat, lon, lang)
            weatherMutableLiveData.value = response
        }
    }
}