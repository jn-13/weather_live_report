package com.example.weatherlivereport.ui.theme

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WeatherViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherReportViewModel::class.java)) {
            return WeatherReportViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
