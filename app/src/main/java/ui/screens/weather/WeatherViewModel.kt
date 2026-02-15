package com.example.weatherapp.ui.screens.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val repo = WeatherRepository()

    private val _state = MutableStateFlow<Result>(Result.Loading)
    val state = _state.asStateFlow()

    private val _city = MutableStateFlow("")
    val city = _city.asStateFlow()

    fun updateCity(newCity: String) {
        _city.value = newCity
    }

    fun loadWeather() {
        if (_city.value.isBlank()) return
        viewModelScope.launch {
            _state.value = Result.Loading
            _state.value = repo.getWeather(_city.value)
        }
    }
}