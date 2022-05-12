package com.sammengistu.quicnetworking.data.source.weather.remote

import com.sammengistu.quicnetworking.data.models.CurrentWeather
import com.sammengistu.quicnetworking.data.source.Result
import com.sammengistu.quicnetworking.di.scope.IoDispatcher
import com.sammengistu.quicnetworking.data.source.weather.remote.retrofit.WeatherApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val weatherApiService: WeatherApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): WeatherRemoteDataSource {

    override suspend fun getCurrentWeather(lat: String, long: String, unit: String): Result<CurrentWeather?> {
        if (lat.isBlank() || long.isBlank() || unit.isBlank()) {
            return Result.Error(
                IllegalArgumentException("Can't accept empty string for lat, long, or unit.")
            )
        }
       return withContext(ioDispatcher) {
            return@withContext try {
                val response = weatherApiService.getCurrentWeather(lat, long, unit)
                if (response.isSuccessful) {
                    Result.Success(response.body())
                } else {
                    Result.Error(Exception(response.errorBody().toString()))
                }
            } catch (exception: Exception) {
                Result.Error(exception)
            }
        }
    }
}