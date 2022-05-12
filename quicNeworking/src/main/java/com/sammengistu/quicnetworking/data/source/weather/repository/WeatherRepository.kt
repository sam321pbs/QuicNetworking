package com.sammengistu.quicnetworking.data.source.weather.repository

import com.sammengistu.quicnetworking.data.models.CurrentWeather
import com.sammengistu.quicnetworking.data.source.Result

interface WeatherRepository {
    suspend fun getCurrentWeather(lat: String, long: String, unit: String): Result<CurrentWeather?>
}