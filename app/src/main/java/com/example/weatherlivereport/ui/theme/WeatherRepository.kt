package com.example.weatherlivereport.ui.theme

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherlivereport.BuildConfig
import android.content.Context

class WeatherRepository(private val context: Context) {

    private val _weatherData = MutableLiveData<ResponseWeather>()
    val weatherData: LiveData<ResponseWeather> get() = _weatherData

    private val apiService = WeatherRetrofitInstance.api
    private val apiKey = BuildConfig.OPENWEATHERMAP_API_KEY
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("weather_prefs", Context.MODE_PRIVATE)


    suspend fun fetchWeather(loc: String) {
        try {
            val response = apiService.getCurrentWeather(loc, apiKey)
            _weatherData.postValue(response)
            saveWeatherData(response)
        } catch (e: Exception) {
            e.printStackTrace()
            this.loadingCachedWeather()
        }
    }

    private fun saveWeatherData(response: ResponseWeather) {
        with(sharedPreferences.edit()) {
            putString("location", response.name)
            putFloat("temperature", response.main.temp.toFloat())
            putString("description", response.weather[0].description)
            putString("iconUrl", "https://openweathermap.org/img/wn/${response.weather[0].icon}.png")
            putLong("sunRise", response.sys.sunrise)
            putLong("sunSet", response.sys.sunset)
            putInt("humidity", response.main.humidity)
            apply()
        }
    }

    fun loadingCachedWeather() {
        val location = sharedPreferences.getString("location", null)
        val temperature = sharedPreferences.getFloat("temperature", Float.MIN_VALUE)
        val description = sharedPreferences.getString("description", null)
        val iconUrl = sharedPreferences.getString("iconUrl", null)
        val sunRise = sharedPreferences.getLong("sunRise", 0)
        val sunSet = sharedPreferences.getLong("sunSet", 0)
        val humidity = sharedPreferences.getInt("humidity", 0)

        if (location != null && temperature != Float.MIN_VALUE && description != null && iconUrl != null) {
            val cachedWeather = ResponseWeather(
                weather = listOf(Weather(main = "", description = description, icon = iconUrl, id = 0)),
                main = Main(temp = temperature.toDouble(), humidity = humidity, feels_like = 0.0, temp_max = 0.0, temp_min = 0.0, pressure = 0, sea_level = 0, grnd_level = 0),
                wind = Wind(speed = 0.0,0),
                sys = Sys(country = "", sunrise = sunRise, sunset = sunSet, type = 0, id = 0),
                name = location,
                coord = Coord(lon= 74.0,lat=26.0),
                base = "stations",
                visibility = 0,
                clouds = Clouds(0),
                dt = 0,
                timezone = 0,
                cod = 0,
                id = 0
            )
            _weatherData.postValue(cachedWeather)
        }
    }
}