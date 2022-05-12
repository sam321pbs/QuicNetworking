package com.sammengistu.quicnetworking.data.source.weather.remote

import com.sammengistu.quicnetworking.data.models.CurrentWeather
import com.sammengistu.quicnetworking.data.source.Result

interface WeatherRemoteDataSource {
    suspend fun getCurrentWeather(lat: String, long: String, unit: String): Result<CurrentWeather?>
}