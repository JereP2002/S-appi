package com.example.weatherapp.ui.screens.weather

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.util.Result

@Composable
fun WeatherScreen(vm: WeatherViewModel = viewModel()) {
    val city by vm.city.collectAsState()
    val state by vm.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = city,
            onValueChange = { vm.updateCity(it) },
            label = { Text("Syötä kaupunki") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { vm.loadWeather() }) {
            Text("Hae sää")
        }
        Spacer(modifier = Modifier.height(16.dp))

        when (state) {
            is Result.Loading -> {
                // Voit jättää tyhjäksi tai laittaa CircularProgressIndicator()
            }
            is Result.Success -> {
                val data = (state as Result.Success).data
                Text(text = data.name, style = MaterialTheme.typography.headlineMedium)
                Text(text = "${data.main.temp} °C", style = MaterialTheme.typography.displayMedium)
                Text(text = data.weather.firstOrNull()?.description ?: "")
            }
            is Result.Error -> {
                Text(
                    text = (state as Result.Error).message,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}