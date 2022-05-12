package com.sammengistu.quicnetworking.data.models

data class CurrentWeather(
    val id: Double,
    val name: String,
    val weather: List<Weather?>,
    val main: Main,
    val wind: Wind,
    val rain: Rain,
    val snow: Snow
)

data class Weather(
    val id: Double,
    val main: String,
    val description: String,
    val icon: String
)

data class Main(
    val temp: Double,
    val temp_min: Double,
    val temp_max: Double,
    val humidity: Double
)

data class Wind(
    val speed: String,
    val deg: String
)

data class Rain(
    val oneH: Double,
    val threeH: Double
)

data class Snow(
    val oneH: Double,
    val threeH: Double
)