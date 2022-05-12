package com.sammengistu.quicnetworking.data.source.weather.repository

import com.sammengistu.quicnetworking.data.models.CurrentWeather
import com.sammengistu.quicnetworking.data.source.Result
import com.sammengistu.quicnetworking.data.source.weather.remote.WeatherRemoteDataSource
import com.sammengistu.quicnetworking.di.scope.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: WeatherRemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): WeatherRepository {
    override suspend fun getCurrentWeather(lat: String, long: String, unit: String): Result<CurrentWeather?> {
        return withContext(ioDispatcher) {
            when(val result = remoteDataSource.getCurrentWeather(lat, long, unit)) {
                is Result.Success -> Result.Success(result.data)
                is Result.Error -> Result.Error(result.exception)
            }
        }
    }
}