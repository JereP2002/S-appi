package com.example.weatherapp.util

import com.example.weatherapp.data.model.WeatherResponse

sealed class Result {
    object Loading : Result()
    data class Success(val data: WeatherResponse) : Result()
    data class Error(val message: String) : Result()
}