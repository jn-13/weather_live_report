package com.example.weatherlivereport

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherlivereport.databinding.ActivityMainBinding
import com.example.weatherlivereport.ui.theme.WeatherReportViewModel
import com.bumptech.glide.Glide
import com.example.weatherlivereport.ui.theme.WeatherViewModelFactory
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


class MainActivity : ComponentActivity() {

    private lateinit var viewModel: WeatherReportViewModel
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = WeatherViewModelFactory(applicationContext)
        viewModel = ViewModelProvider(this, factory)[WeatherReportViewModel::class.java]

        viewModel.fetchWeather("bangalore")

        viewModel.weatherData.observe(this, Observer { resWeather ->
            binding.currentTempTextView.text = "${String.format("%.2f", resWeather.main.temp - 273.15)} °C"
            binding.humidityTextView.text = "Humidity: ${resWeather.main.humidity}%"
            binding.locationTextView.text = resWeather.name
            binding.descriptionText.text = resWeather.weather[0].description


            val iconUrl = "https://openweathermap.org/img/w/${resWeather.weather[0].icon}.png"
            Glide.with(this).load(iconUrl
            ).into(binding.weatherIconImageView)
            Glide.with(this).load(R.drawable.ic_sunrise_icon
            ).skipMemoryCache(true).into(binding.sunriseIcon)
            Glide.with(this).load(R.drawable.ic_sunset
            ).skipMemoryCache(true).into(binding.sunsetIcon)
            this.timeConverter(resWeather.sys.sunrise, resWeather.sys.sunset)

            if (!isNetworkAvailable(this)) {
               viewModel.loadingCachedWeather()
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun timeConverter(sunriseTimestamp: Long, sunsetTimestamp: Long) {

        val sunriseInstant = Instant.ofEpochSecond(sunriseTimestamp)
        val sunsetInstant = Instant.ofEpochSecond(sunsetTimestamp)

        val istZoneId = ZoneId.of("Asia/Kolkata")

        val sunriseTime = ZonedDateTime.ofInstant(sunriseInstant, istZoneId)
        val sunsetTime = ZonedDateTime.ofInstant(sunsetInstant, istZoneId)

        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")

        binding.sunriseTime.text = sunriseTime.format(formatter)
        binding.sunsetTime.text = sunsetTime.format(formatter)

    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}
