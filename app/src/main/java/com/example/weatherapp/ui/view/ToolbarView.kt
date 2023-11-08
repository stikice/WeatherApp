package com.example.weatherapp.ui.view

import android.content.Intent
import android.content.res.Configuration
import androidx.fragment.app.Fragment
import com.example.weatherapp.LocaleManager
import com.example.weatherapp.MainActivity
import com.example.weatherapp.databinding.LayoutToolbarBinding
import java.util.Locale

class ToolbarView(
    toolbarLayout: LayoutToolbarBinding,
    private val fragment: Fragment
) {
    init {
        toolbarLayout.languageButton.setOnClickListener {
            if (LocaleManager.language != "ru" && LocaleManager.language != "kk") {
                setLocale(Locale("ru"))
            } else if (LocaleManager.language != "kk") {
                setLocale(Locale("kk"))
            } else {
                setLocale(Locale("en"))
            }
        }
    }

    private fun setLocale(locale: Locale) = with(fragment) {
        LocaleManager.language = locale.language
        val configuration: Configuration = resources.configuration
        configuration.setLocale(locale)
        requireContext().startActivity(Intent(context, MainActivity::class.java))
        activity?.finish()
    }
}