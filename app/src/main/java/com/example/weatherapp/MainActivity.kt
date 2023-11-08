package com.example.weatherapp

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment

const val TABLE = "table"

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = this.getSharedPreferences(TABLE, Context.MODE_PRIVATE)

        val isHaveCities = sharedPreferences.getBoolean(
            TABLE,
            false
        )

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)

        if (isHaveCities) {
            navGraph.setStartDestination(R.id.mainFragment)
        } else {
            navGraph.setStartDestination(R.id.addCityFragment)
        }

        navController.graph = navGraph

    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        setLocale(newBase)
    }

    private fun setLocale(newBase: Context?) {
        val configuration = Configuration(newBase?.resources?.configuration)
        configuration.setLocale(LocaleManager.locale)
        applyOverrideConfiguration(configuration)
    }
}