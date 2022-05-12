package com.sammengistu.quicnetworking.data.source.weather.remote.retrofit

import com.sammengistu.quicnetworking.data.models.CurrentWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// https://openweathermap.org/current#current_JSON
interface WeatherApiService {

    @GET("/data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: String,
        @Query("lon") long: String,
        @Query("units") units: String
    ): Response<CurrentWeather?>
}