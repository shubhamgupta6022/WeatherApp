package com.example.weather.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.WeatherForecastCardBinding
import com.example.weather.domain.model.ForeCastWeatherItem

class WeatherForecastAdapter(val list: List<ForeCastWeatherItem>?) :
    RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherForecastAdapter.ViewHolder {
        val binding =
            WeatherForecastCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherForecastAdapter.ViewHolder, position: Int) {
        holder.bind(position, list?.get(position) )
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    inner class ViewHolder(val binding: WeatherForecastCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int, model: ForeCastWeatherItem?) {
            with(binding) {
                tvDay.text = model?.date
                tvTemperature.text = model?.tempInCelsius
            }
        }

    }

}