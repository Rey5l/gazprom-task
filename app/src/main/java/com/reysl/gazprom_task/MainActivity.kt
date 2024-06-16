package com.reysl.gazprom_task

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reysl.gazprom_task.model.City
import com.reysl.gazprom_task.network.CityApi
import com.reysl.gazprom_task.ui.main.CityAdapter
import com.reysl.gazprom_task.ui.main.StickyHeaderItemDecoration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorMessage: TextView
    private lateinit var reloadButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.city_list)
        progressBar = findViewById(R.id.progress_bar)
        errorMessage = findViewById(R.id.text_error)
        reloadButton = findViewById(R.id.reload_button)

        recyclerView.layoutManager = LinearLayoutManager(this)

        reloadButton.setOnClickListener {
            loadCities()
        }

        loadCities()
    }

    private fun loadCities() {
        progressBar.visibility = View.VISIBLE
        errorMessage.visibility = View.GONE
        reloadButton.visibility = View.GONE

        CityApi.cityService.getCities().enqueue(object : Callback<List<City>> {
            override fun onResponse(call: Call<List<City>>, response: Response<List<City>>) {
                progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    val cities = response.body()
                    if (cities != null) {
                        val sortedCities = cities.filter { it.city.isNotBlank() }.sortedBy { it.city }
                        val adapter = CityAdapter(sortedCities) {}
                        recyclerView.adapter = adapter
                        recyclerView.addItemDecoration(StickyHeaderItemDecoration(adapter))
                    }
                } else {
                    showError()
                }
            }

            override fun onFailure(call: Call<List<City>>, t: Throwable) {
                progressBar.visibility = View.GONE
                showError()
            }
        })
    }

    private fun showError() {
        errorMessage.visibility = View.VISIBLE
        reloadButton.visibility = View.VISIBLE
        recyclerView.adapter = null
    }
}
