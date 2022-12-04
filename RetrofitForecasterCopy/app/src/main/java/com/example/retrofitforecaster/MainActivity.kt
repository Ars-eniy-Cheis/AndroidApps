package com.example.retrofitforecaster

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydialer.CellClickListener
import com.example.mydialer.WeatherAdapter
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), CellClickListener {

    val WEATHER = "weather"
    lateinit var mService: RetrofitServices
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: WeatherAdapter
    lateinit var dialog: AlertDialog
    lateinit var recyclerView: RecyclerView
    var weatherList: ArrayList<WeatherList> = arrayListOf()
    var wthJson: String = ""

    object WeatherStore {
        var weathers: List<WeatherInfo>? = null
            //get() = weathers
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = WeatherAdapter(this)
        mService = Common.retrofitService
        recyclerView = findViewById(R.id.rView)
        recyclerView.adapter = adapter
        adapter.submitList(weatherList)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        if (WeatherStore.weathers == null){
            getWeatherList()
        }

        else{
            weatherList.addAll(WeatherStore.weathers as Collection<WeatherList>)
            adapter.notifyDataSetChanged()
        }


    }

    fun getWeatherList(){
        mService.getWeatherInfo().enqueue(object : Callback<WeatherInfo>{

            override fun onFailure(call: Call<WeatherInfo>, t: Throwable) {
                Log.v("Fail", t.toString())
            }

            override fun onResponse(call: Call<WeatherInfo>, response: Response<WeatherInfo>) {
                Log.v("Weather", response.body().toString())
                var wth = response.body() as WeatherInfo
                weatherList.addAll(wth.list)
                wthJson = Gson().toJson(response.body())
                adapter.notifyDataSetChanged()
                WeatherStore.weathers = wth.list as List<WeatherInfo>?
            }
        })
    }

    override fun onCellClickListener(data: WeatherList) {
        var str: String = ""
    }

    override fun onSaveInstanceState(saveInstanceState: Bundle) {
        saveInstanceState.putString(WEATHER, wthJson)
        super.onSaveInstanceState(saveInstanceState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState)

        wthJson = savedInstanceState.getString(WEATHER).toString()
    }
}

