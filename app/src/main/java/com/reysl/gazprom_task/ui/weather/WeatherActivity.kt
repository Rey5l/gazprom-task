package com.reysl.gazprom_task.ui.weather

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.reysl.gazprom_task.R
import com.reysl.gazprom_task.model.WeatherResponse
import com.reysl.gazprom_task.network.WeatherApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherActivity : AppCompatActivity() {
    private val apiKey = "68df7e91b117c6b3827456f89f50268b"
    private lateinit var progressBar: ProgressBar
    private lateinit var errorText: TextView
    private lateinit var reloadButton: Button
    private lateinit var cityNameTextView: TextView
    private lateinit var degreesTextView: TextView
    private lateinit var reloadButton2: Button
    private lateinit var celsius: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val cityName = intent.getStringExtra("cityName")
        cityNameTextView = findViewById(R.id.city_name)
        degreesTextView = findViewById(R.id.degrees)
        progressBar = findViewById(R.id.progress_bar)
        errorText = findViewById(R.id.text_error)
        reloadButton = findViewById(R.id.reload_button)
        reloadButton2 = findViewById(R.id.reload_button2)
        celsius = findViewById(R.id.celsius)

        cityNameTextView.text = cityName

        if (cityName != null) {
            getWeatherForCity(cityName, degreesTextView)
        }

        reloadButton.setOnClickListener {
            if (cityName != null) {
                getWeatherForCity(cityName, degreesTextView)
            }
        }
        reloadButton2.setOnClickListener {
            if (cityName != null) {
                getWeatherForCity(cityName, degreesTextView)
            }
        }

    }

    private fun getWeatherForCity(cityName: String, degreesTextView: TextView) {
        progressBar.visibility = View.VISIBLE
        reloadButton.visibility = View.GONE
        errorText.visibility = View.GONE

        WeatherApi.weatherService.getWeather(cityName, apiKey).enqueue(object :
            Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                    if (weatherResponse != null) {
                        val temp = weatherResponse.main.temp.toInt()
                        degreesTextView.text = temp.toString()
                    }
                } else {
                    showError()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                progressBar.visibility = View.GONE
                showError()
            }
        })
    }

    private fun showError() {
        reloadButton.visibility = View.VISIBLE
        errorText.visibility = View.VISIBLE
        cityNameTextView.visibility = View.GONE
        degreesTextView.visibility = View.GONE
        celsius.visibility = View.GONE
        reloadButton2.visibility = View.GONE
    }
}