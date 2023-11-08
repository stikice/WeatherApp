package com.example.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemHourForecastBinding

class WeatherHourAdapter() : ListAdapter<WeatherHour, WeatherHourViewHolder>(WeatherHourDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHourViewHolder {
        val binding = ItemHourForecastBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WeatherHourViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherHourViewHolder, position: Int) {
        holder.bind(getItem(position), position == 0)
    }

}

class WeatherHourViewHolder(private val binding: ItemHourForecastBinding) : ViewHolder(binding.root) {
    fun bind(item: WeatherHour, isFirst: Boolean) = with(binding) {
        dateTextView.text = if (isFirst) {
            itemView.context.getString(R.string.now)
        } else {
            item.time
        }
        iconImageView.load(item.icon)
        temperatureTextView.text = item.temp
    }
}



object WeatherHourDiff : DiffUtil.ItemCallback<WeatherHour>() {
    override fun areItemsTheSame(
        oldItem: WeatherHour,
        newItem: WeatherHour
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: WeatherHour,
        newItem: WeatherHour
    ): Boolean {
        return oldItem == newItem
    }
}