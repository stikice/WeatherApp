package com.example.weatherapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weatherapp.domain.model.LocalCity
import com.example.weatherapp.ui.fragment.WeatherFragment

class WeatherCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val cityList: MutableList<LocalCity> = mutableListOf()

    override fun getItemCount(): Int = cityList.size

    override fun createFragment(position: Int): Fragment {
        return WeatherFragment.newInstance(cityList[position])
    }

    fun submitCities(cities: List<LocalCity>) {
        cityList.clear()
        cityList.addAll(cities)
    }

}