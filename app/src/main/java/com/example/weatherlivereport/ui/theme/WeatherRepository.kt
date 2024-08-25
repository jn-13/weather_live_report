package com.example.weatherlivereport.ui.theme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherlivereport.BuildConfig

class WeatherRepository {

    private val _weatherData = MutableLiveData<ResponseWeather>()
    val weatherData: LiveData<ResponseWeather> get() = _weatherData

    private val apiService = WeatherRetrofitInstance.api
    private val apiKey = BuildConfig.OPENWEATHERMAP_API_KEY

    suspend fun fetchWeather(loc: String) {
        try {
            val response = apiService.getCurrentWeather(loc, apiKey)
            _weatherData.postValue(response)
        } catch (e: Exception) {
            // Handle error appropriately
            e.printStackTrace()
        }
    }
}