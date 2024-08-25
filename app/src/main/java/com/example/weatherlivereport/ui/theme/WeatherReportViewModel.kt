package com.example.weatherlivereport.ui.theme

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WeatherReportViewModel: ViewModel() {
    private val repository = WeatherRepository()
    val weatherData: LiveData<ResponseWeather> = repository.weatherData

    fun fetchWeather(cityName: String) {
        viewModelScope.launch {
            repository.fetchWeather(cityName)
        }
    }

}