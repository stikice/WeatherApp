package com.example.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemDailyForecastBinding

class WeatherDailyAdapter: ListAdapter<WeatherDaily, WeatherDailyViewHolder>(WeatherDailyDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDailyViewHolder {
        val binding = ItemDailyForecastBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WeatherDailyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherDailyViewHolder, position: Int) {
        holder.bind(getItem(position), position == 0)
    }

}

class WeatherDailyViewHolder(private val binding: ItemDailyForecastBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: WeatherDaily, isFirst: Boolean) = with(binding) {
        dayOfWeekTextView.text = if (isFirst) {
            itemView.context.getString(R.string.today).lowercase().replaceFirstChar { it.uppercase() }
        } else {
            item.day
        }
        iconImageView.load(item.icon)
        popTextView.isVisible = item.pop.isNotEmpty()
        popTextView.text = item.pop
        minTempTextView.text = item.minTemp
        maxTempTextView.text = item.maxTemp
    }
}

object WeatherDailyDiff : DiffUtil.ItemCallback<WeatherDaily>() {
    override fun areItemsTheSame(
        oldItem: WeatherDaily,
        newItem: WeatherDaily
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: WeatherDaily,
        newItem: WeatherDaily
    ): Boolean {
        return oldItem == newItem
    }
}