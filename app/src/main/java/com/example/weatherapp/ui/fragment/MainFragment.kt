package com.example.weatherapp.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.TABLE
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.ui.adapter.WeatherCollectionAdapter
import com.example.weatherapp.ui.view.ToolbarView
import com.example.weatherapp.ui.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModel()
    private var navigated: Boolean = false

    private lateinit var weatherCollectionAdapter: WeatherCollectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments?.let {
            navigated = it.getBoolean(LAST)
        }
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences(TABLE, Context.MODE_PRIVATE)

        ToolbarView(toolbarLayout = toolbarLayout, fragment = this@MainFragment)

        weatherCollectionAdapter = WeatherCollectionAdapter(this@MainFragment)

        fab.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addCityFragment)
        }

        viewPager.adapter = weatherCollectionAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position -> }.attach()

        bottomAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.delete -> {
                    val currentCity = viewModel.citiesLiveData.value?.get(viewPager.currentItem)
                    if(currentCity != null) {
                        viewModel.deleteCity(currentCity)
                    }
                    weatherCollectionAdapter.notifyItemRemoved(viewPager.currentItem)
                    true
                }
                else -> false
            }
        }

        viewModel.getCities()
        viewModel.citiesLiveData.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {
                sharedPreferences.edit {
                    putBoolean(TABLE, false)
                }
            } else {
                sharedPreferences.edit {
                    putBoolean(TABLE, true)
                }
            }
            weatherCollectionAdapter.submitCities(it.orEmpty())
            weatherCollectionAdapter.notifyDataSetChanged()

            if (navigated && !it.isNullOrEmpty()) {
                viewPager.currentItem = it.size - 1
            }
        }
    }

    companion object {
        const val LAST = "last"
    }
}