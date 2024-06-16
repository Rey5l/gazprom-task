package com.reysl.gazprom_task

object CityApi {
    val cityService: CityService by lazy {
        RetrofitClient.instance.create(CityService::class.java)
    }
}