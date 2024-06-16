package com.reysl.gazprom_task

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class CityAdapter(private val cities: List<City>, private val onCityClick: (City) -> Unit) :
    RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]
        holder.cityNameTextView.text = city.city

        val currentLabel = city.city.substring(0, 1).uppercase(Locale.getDefault())

        if (position == 0 || currentLabel != cities[position - 1].city.substring(0, 1).uppercase(Locale.getDefault())) {
            holder.cityLabel.visibility = View.VISIBLE
            holder.cityLabel.text = currentLabel
        } else {
            holder.cityLabel.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, WeatherActivity::class.java)
            intent.putExtra("cityName", city.city)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityNameTextView: TextView = itemView.findViewById(R.id.city_name)
        val cityLabel: TextView = itemView.findViewById(R.id.cityLabel)
    }
}
