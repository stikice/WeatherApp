package com.example.weatherapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.cities
import com.example.weatherapp.databinding.FragmentAddCityBinding
import com.example.weatherapp.ui.view.ToolbarView

class AddCityFragment : Fragment() {

    private lateinit var binding: FragmentAddCityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        ToolbarView(toolbarLayout = toolbarLayout, fragment = this@AddCityFragment)

        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, cities.map { it.inLocaleLanguage(resources) }.sortedBy { it })
        menuAutoCompleTextView.setAdapter(adapter)

        menuInputLayout.editText?.addTextChangedListener {
            if (!menuInputLayout.error.isNullOrBlank() && menuAutoCompleTextView.text.isNotBlank()) {
                menuInputLayout.error = null
            }
        }

        nextButton.setOnClickListener {
            if (menuAutoCompleTextView.text.isNotBlank()) {
                findNavController().navigate(R.id.action_addCityFragment_to_welcomeCityFragment, bundleOf(
                    WelcomeCityFragment.CITY to menuAutoCompleTextView.text.toString()))
                menuAutoCompleTextView.text.clear()
            } else {
                menuInputLayout.error = getString(R.string.error_city_is_empty)
            }
        }
    }
}