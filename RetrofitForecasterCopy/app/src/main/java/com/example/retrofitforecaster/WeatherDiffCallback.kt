package com.example.mydialer

import androidx.recyclerview.widget.DiffUtil
import com.example.retrofitforecaster.WeatherInfo
import com.example.retrofitforecaster.WeatherList

class WeatherDiffCallback: DiffUtil.ItemCallback<WeatherList>() {
    override fun areItemsTheSame(oldItem: WeatherList, newItem: WeatherList): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: WeatherList, newItem: WeatherList): Boolean {
        return oldItem == newItem
    }
}