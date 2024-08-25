package com.example.weatherlivereport.ui.theme

import retrofit2.http.GET
import retrofit2.http.Query
import com.example.weatherlivereport.ui.theme.ResponseWeather

interface WeatherApiService {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") location: String,
        @Query("appid") apiKey: String
    ): ResponseWeather

}