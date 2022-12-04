package com.example.mydialer

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitforecaster.R
import com.example.retrofitforecaster.WeatherList

const val HOT_TEMPERATURE: Int = 1
const val COLD_TEMPERATURE: Int = 2


class HotViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val textDate: TextView = view.findViewById(R.id.textDateHot)
    val imageWeather: TextView = view.findViewById(R.id.imageWeatherHot)
    val textTemperature: TextView = view.findViewById(R.id.textTemperatureHot)

    @RequiresApi(Build.VERSION_CODES.O)
    fun bindTo(weatherList: WeatherList){
        var time = java.time.format.DateTimeFormatter.ISO_INSTANT
            .format((weatherList.dt?.plus(3600 * 3))?.let { java.time.Instant.ofEpochSecond(it.toLong()) }).toString().replace('T', ' ').replace('Z', ' ')
        textDate.text = time
        textTemperature.text = weatherList.main?.temp.toString()
        imageWeather.text = String(Character.toChars(0x2668))

    }

}

class ColdViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val textDate: TextView = view.findViewById(R.id.textDateCold)
    val imageWeather: TextView = view.findViewById(R.id.imageWeatherCold)
    val textTemperature: TextView = view.findViewById(R.id.textTemperatureCold)

    @RequiresApi(Build.VERSION_CODES.O)
    fun bindTo(weatherList: WeatherList){

        var time = java.time.format.DateTimeFormatter.ISO_INSTANT
            .format((weatherList.dt?.plus(3600 * 3))?.let { java.time.Instant.ofEpochSecond(it.toLong()) }).toString().replace('T', ' ').replace('Z', ' ')
        textDate.text = time
        textTemperature.text = weatherList.main?.temp.toString()
        imageWeather.text = String(Character.toChars(0x2744))

    }

}


class WeatherAdapter(private val cellClickListener: CellClickListener): ListAdapter<WeatherList, RecyclerView.ViewHolder>(WeatherDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == HOT_TEMPERATURE){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.rview_item_hot,parent, false)
            return HotViewHolder(view)
        }
        else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.rview_item_cold,parent, false)
            return ColdViewHolder(view)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].main?.temp!! >= 0) {
            HOT_TEMPERATURE
        } else {
            COLD_TEMPERATURE
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = currentList[position]
        if (getItemViewType(position) === HOT_TEMPERATURE) {
            (holder as HotViewHolder).bindTo(data)
        } else {
            (holder as ColdViewHolder).bindTo(data)
        }

        holder.itemView.setOnClickListener{
            cellClickListener.onCellClickListener(data)
        }
    }
}