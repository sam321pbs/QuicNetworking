package com.sammengistu.quicnetworking.weather

import com.sammengistu.quicnetworking.data.models.*

internal class WeatherTestConstants {
    companion object {
        const val LAT = "0"
        const val LONG = "0"
        const val UNIT = "Unit"

        val fakeWeather = CurrentWeather(
            0.0,
            "Name",
            listOf(Weather(
                0.0,
                "Main",
                "Description",
                "Icon"
            )),
            Main(
                90.0,
                0.0,
                100.0,
                20.0
            ),
            Wind(
                "Speed",
                "Deg"
            ),
            Rain(
                1.0,
                3.0
            ),
            Snow(
                1.0,
                3.0
            )
        )
    }
}