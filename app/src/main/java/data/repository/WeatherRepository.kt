package com.example.weatherapp.data.repository

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.remote.RetrofitInstance
import com.example.weatherapp.util.Result

class WeatherRepository {
    private val api = RetrofitInstance.api
    private val apiKey = BuildConfig.OPENWEATHER_API_KEY

    suspend fun getWeather(city: String): Result {
        return try {
            val response = api.getWeather(city, apiKey)
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error("Haku epäonnistui: ${e.localizedMessage}")
        }
    }
}