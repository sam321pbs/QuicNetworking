package com.sammengistu.quicnetworking.weather

import com.sammengistu.quicnetworking.data.models.CurrentWeather
import com.sammengistu.quicnetworking.data.source.Result
import com.sammengistu.quicnetworking.data.source.weather.remote.WeatherRemoteDataSource
import com.sammengistu.quicnetworking.data.source.weather.repository.WeatherRepository
import com.sammengistu.quicnetworking.data.source.weather.repository.WeatherRepositoryImpl
import com.sammengistu.quicnetworking.weather.WeatherTestConstants.Companion.LAT
import com.sammengistu.quicnetworking.weather.WeatherTestConstants.Companion.LONG
import com.sammengistu.quicnetworking.weather.WeatherTestConstants.Companion.UNIT
import com.sammengistu.quicnetworking.weather.WeatherTestConstants.Companion.fakeWeather
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class WeatherRepositoryTest {

    private val dataSource: WeatherRemoteDataSource = mock(WeatherRemoteDataSource::class.java)
    private lateinit var repo: WeatherRepository

    @Before
    fun setup() {
        repo = WeatherRepositoryImpl(dataSource, Dispatchers.IO)
    }

    @Test
    fun `getCurrentWeather returns data with valid input`() = runBlocking {
        val result = Result.Success(fakeWeather)

        `when`(dataSource.getCurrentWeather(LAT, LONG, UNIT)).thenReturn(result)

        val output = repo.getCurrentWeather(LAT, LONG, UNIT)

        assert(output is Result.Success<CurrentWeather>)

        val outputCurrentWeather = (output as Result.Success<CurrentWeather>).data?.weather?.get(0)

        assertEquals(outputCurrentWeather?.main, "Main")
        assertEquals(outputCurrentWeather?.icon, "Icon")
    }

    @Test
    fun `getCurrentWeather returns data with invalid lat`() = runBlocking {
        val result = Result.Error(IllegalArgumentException())

        `when`(dataSource.getCurrentWeather("", LONG, UNIT)).thenReturn(result)

        val output = repo.getCurrentWeather("", LONG, UNIT)

        assert(output is Result.Error)
        assert((output as Result.Error).exception is IllegalArgumentException)
    }

    @Test
    fun `getCurrentWeather returns data with invalid long`() = runBlocking {
        val result = Result.Error(IllegalArgumentException())

        `when`(dataSource.getCurrentWeather(LAT, "", UNIT)).thenReturn(result)

        val output = repo.getCurrentWeather(LAT, "", UNIT)

        assert(output is Result.Error)
        assert((output as Result.Error).exception is IllegalArgumentException)
    }
}