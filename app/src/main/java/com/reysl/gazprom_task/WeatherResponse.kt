package com.reysl.gazprom_task

data class WeatherResponse(
    val main: Main
)

data class Main(
    val temp: Double
)
