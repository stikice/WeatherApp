package com.example.weatherapp

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import java.util.Locale

object LocaleManager {

    private const val LANGUAGE = "language"

    private var lang: String? = null

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(LANGUAGE, Context.MODE_PRIVATE)
    }

    val locale: Locale
        get() = Locale(language)

    var language: String
        get() {
            return try {
                if (sharedPreferences.contains(LANGUAGE) && lang == null) {
                    lang = sharedPreferences.getString(
                            LANGUAGE,
                            "en"
                        )
                }
                lang ?: "en"
            } catch (e: Exception) {
                "en"
            }
        }
        set(value) {
            sharedPreferences.edit {
                putString(LANGUAGE, value)
            }
            lang = value
        }

}