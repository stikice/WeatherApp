package com.example.weatherapp.di

import com.example.weatherapp.data.api.CityApi
import com.example.weatherapp.data.api.WeatherApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val API_KEY = "8f2a554e1d1b8e49d7ce0e283817eb7a"

val networkModule = module {
    single {
        val okHttpClient: OkHttpClient = OkHttpClient().newBuilder().addInterceptor { chain ->
            val original = chain.request()

            // Request customization: add request headers
            val url = original.url().newBuilder()
                .addQueryParameter("appid", API_KEY).build()

            val request = original.newBuilder().url(url).build()
            chain.proceed(request)
        }.build()
        okHttpClient
    }

    single {
        val weatherApi: WeatherApi = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(WeatherApi::class.java)
        weatherApi
    }

    single {
        val cityApi: CityApi = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/geo/1.0/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(CityApi::class.java)
        cityApi
    }
}



