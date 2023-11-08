package com.example.weatherapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.data.model.WeatherForecast
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.domain.model.LocalCity
import com.example.weatherapp.ui.adapter.WeatherDaily
import com.example.weatherapp.ui.adapter.WeatherDailyAdapter
import com.example.weatherapp.ui.adapter.WeatherHour
import com.example.weatherapp.ui.adapter.WeatherHourAdapter
import com.example.weatherapp.ui.convertToDay
import com.example.weatherapp.ui.convertToDayOfWeek
import com.example.weatherapp.ui.convertToHour
import com.example.weatherapp.ui.makePercentage
import com.example.weatherapp.ui.makeStaticImageUrl
import com.example.weatherapp.ui.makeTemperature
import com.example.weatherapp.ui.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherFragment : Fragment() {

    private val viewModel: WeatherViewModel by viewModel()

    private lateinit var binding: FragmentWeatherBinding
    private lateinit var city: LocalCity

    private val hourAdapter = WeatherHourAdapter()
    private val dailyAdapter = WeatherDailyAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments?.let {
            city = it.getParcelable(LOCALCITY)!!
        }
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        hourRecyclerView.adapter = hourAdapter
        dailyRecyclerView.adapter = dailyAdapter

        viewModel.getName(city.lat, city.lon)
        val lang = when (resources.configuration.locale.language) {
            "kk" -> "ru"
            else -> resources.configuration.locale.language
        }
        viewModel.getWeather(city.lat, city.lon, lang)

    }

    private fun initObservers() = with(binding) {
        viewModel.cityNameLiveData.observe(viewLifecycleOwner) {
            cityNameTextView.text = it?.inLocaleLanguage(resources) ?: it?.ru ?: it?.en
        }
        viewModel.weatherLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                val today = it.list.first()
                nestedScrollView.setBackgroundColor(
                    when (today.sys.pod) {
                        "d" -> resources.getColor(R.color.background_day)
                        else -> resources.getColor(R.color.background_night)
                    }
                )
                temperatureTextView.text =
                    getString(R.string.temperature).format(today.main.temp.toInt())
                descriptionTextView.text = today.weather.first().description
                minMaxTextView.text = getString(R.string.min_max_temperature).format(
                    today.main.tempMin.toInt(),
                    today.main.tempMax.toInt()
                )
                val listOfFiveDays = it.list
                setupHourRecyclerView(listOfFiveDays)
                setupDailyRecyclerView(listOfFiveDays)
            }
        }
    }

    private fun setupHourRecyclerView(list: List<WeatherForecast>) {
        val hourForecastList = list.take(8).map { it.mapToWeatherHour() }
        hourAdapter.submitList(hourForecastList)
    }

    private fun setupDailyRecyclerView(list: List<WeatherForecast>) {
        val dailyForecastList = mutableListOf<WeatherDaily>()
        val listOfDays = list.groupBy { it.dt.convertToDay() }
        listOfDays.forEach {
            dailyForecastList.add(it.value.first().mapToWeatherDaily())
        }
        dailyAdapter.submitList(dailyForecastList)
    }

    private fun WeatherForecast.mapToWeatherHour(): WeatherHour =
        WeatherHour(
            this.dt.convertToHour(),
            this.weather.first().icon.makeStaticImageUrl(),
            this.main.temp.toInt().makeTemperature()
        )

    private fun WeatherForecast.mapToWeatherDaily(): WeatherDaily {
        val popString = if (this.pop > 0.0) {
            this.pop.makePercentage()
        } else {
            ""
        }
        return WeatherDaily(
            this.dt.convertToDayOfWeek(),
            this.weather.first().icon.makeStaticImageUrl(),
            popString,
            this.main.tempMin.toInt().makeTemperature(),
            this.main.tempMax.toInt().makeTemperature()
        )
    }


    companion object {

        const val LOCALCITY = "localcity"

        fun newInstance(city: LocalCity) = WeatherFragment().apply {
            arguments = bundleOf(Pair(LOCALCITY, city))
        }
    }
}