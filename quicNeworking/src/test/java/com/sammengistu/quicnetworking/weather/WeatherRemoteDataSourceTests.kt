package com.sammengistu.quicnetworking.weather

import com.sammengistu.quicnetworking.data.models.CurrentWeather
import com.sammengistu.quicnetworking.data.source.Result
import com.sammengistu.quicnetworking.data.source.weather.remote.WeatherRemoteDataSource
import com.sammengistu.quicnetworking.data.source.weather.remote.WeatherRemoteDataSourceImpl
import com.sammengistu.quicnetworking.data.source.weather.remote.retrofit.WeatherApiService
import com.sammengistu.quicnetworking.weather.WeatherTestConstants.Companion.LAT
import com.sammengistu.quicnetworking.weather.WeatherTestConstants.Companion.LONG
import com.sammengistu.quicnetworking.weather.WeatherTestConstants.Companion.UNIT
import com.sammengistu.quicnetworking.weather.WeatherTestConstants.Companion.fakeWeather
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response

class WeatherRemoteDataSourceTests {

    private val apiService: WeatherApiService = mock(WeatherApiService::class.java)
    private lateinit var dataSource: WeatherRemoteDataSource

    @Before
    fun setup() {
        dataSource = WeatherRemoteDataSourceImpl(apiService, Dispatchers.IO)
    }

    @Test
    fun `getCurrentWeather returns data with valid input`()  = runBlocking {
        `when`(
            apiService.getCurrentWeather(LAT, LONG, UNIT)
        ).thenReturn(
            Response.success(200, fakeWeather)
        )

        val output = dataSource.getCurrentWeather(LAT, LONG, UNIT)

        assert(output is Result.Success)
        val currentWeather = (output as Result.Success<CurrentWeather>).data
        assertNotNull(currentWeather)
        assertEquals(currentWeather?.id, 0.0)
        assertEquals(currentWeather?.weather?.get(0)?.main, "Main")
    }

    @Test
    fun `getCurrentWeather returns data with api response error`() = runBlocking {
        `when`(
            apiService.getCurrentWeather("", "", "")
        ).thenReturn(Response.error(500, ResponseBody.create(null, "")))

        val output = dataSource.getCurrentWeather(LAT, LONG, UNIT)

        assert(output is Result.Error)
    }

    @Test
    fun `getCurrentWeather returns data with invalid lat and long`() = runBlocking {
        val output = dataSource.getCurrentWeather("", "", UNIT)

        assert(output is Result.Error)
        assert((output as Result.Error).exception is IllegalArgumentException)
    }

    @Test
    fun `getCurrentWeather returns data with invalid lat`() = runBlocking {
        val output = dataSource.getCurrentWeather("", LONG, UNIT)

        assert(output is Result.Error)
        assert((output as Result.Error).exception is IllegalArgumentException)
    }

    @Test
    fun `getCurrentWeather returns data with invalid long`() = runBlocking {
        val output = dataSource.getCurrentWeather(LAT, "", UNIT)

        assert(output is Result.Error)
        assert((output as Result.Error).exception is IllegalArgumentException)
    }
}