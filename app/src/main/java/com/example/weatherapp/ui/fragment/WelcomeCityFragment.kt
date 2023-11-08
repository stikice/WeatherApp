package com.example.weatherapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWelcomeCityBinding
import com.example.weatherapp.domain.model.LocalCity
import com.example.weatherapp.ui.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class WelcomeCityFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeCityBinding
    private var city: String? = null

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            city = it.getString(CITY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        city?.let {
            viewModel.checkCity(it)
        }
        progressBar.isVisible = true
        viewModel.cityLiveData.observe(viewLifecycleOwner) {
            progressBar.isVisible = false
            if (it != null) {
                val cityName = it.localNames.inLocaleLanguage(resources) ?: city
                welcomeTextView.text = getString(R.string.welcome_city).format(cityName)
                viewModel.addCity(LocalCity(name = it.name, lat = it.lat, lon = it.lon))
                viewLifecycleOwner.lifecycleScope.launch {
                    delay(1000)
                    findNavController().navigate(
                        R.id.action_welcomeCityFragment_to_mainFragment,
                        bundleOf(MainFragment.LAST to true)
                    )
                }
            } else {
                welcomeTextView.text = getString(R.string.error_city_not_found)
                viewLifecycleOwner.lifecycleScope.launch {
                    delay(1000)
                    requireActivity().onBackPressed()
                }
            }
        }
    }

    companion object {
        const val CITY = "city"
    }
}