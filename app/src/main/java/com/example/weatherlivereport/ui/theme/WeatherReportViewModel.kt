package com.example.weatherlivereport.ui.theme

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WeatherReportViewModel(context: Context): ViewModel() {
    private val repository = WeatherRepository(context)
    val weatherData: LiveData<ResponseWeather> = repository.weatherData

    fun fetchWeather(cityName: String) {
        viewModelScope.launch {
            repository.fetchWeather(cityName)
        }
    }

    fun loadingCachedWeather() {
        repository.loadingCachedWeather()
    }

}