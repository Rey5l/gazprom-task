package com.reysl.gazprom_task

import retrofit2.Call
import retrofit2.http.GET

interface CityService {
    @GET("Stronger197/764f9886a1e8392ddcae2521437d5a3b/raw/65164ea1af958c75c81a7f0221bead610590448e/cities.json")
    fun getCities(): Call<List<City>>
}