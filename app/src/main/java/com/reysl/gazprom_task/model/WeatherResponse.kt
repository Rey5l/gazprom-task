package com.reysl.gazprom_task.model

data class WeatherResponse(
    val main: Main
)

data class Main(
    val temp: Double
)
