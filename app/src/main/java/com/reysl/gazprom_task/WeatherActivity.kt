package com.reysl.gazprom_task

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherActivity : AppCompatActivity() {
    private val apiKey = "68df7e91b117c6b3827456f89f50268b"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val cityName = intent.getStringExtra("cityName")
        val cityNameTextView: TextView = findViewById(R.id.city_name)
        val degreesTextView: TextView = findViewById(R.id.degrees)

        cityNameTextView.text = cityName

        if (cityName != null) {
            getWeatherForCity(cityName, degreesTextView)
        }
    }

    private fun getWeatherForCity(cityName: String, degreesTextView: TextView) {
        WeatherApi.weatherService.getWeather(cityName, apiKey).enqueue(object :
            Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                    if (weatherResponse != null) {
                        val temp = weatherResponse.main.temp.toInt()
                        degreesTextView.text = temp.toString()
                    }
                } else {
                    Toast.makeText(this@WeatherActivity, "Failed to get weather data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Toast.makeText(this@WeatherActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}