package com.example.retrofitforecaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydialer.CellClickListener
import com.example.mydialer.WeatherAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), CellClickListener {

    lateinit var mService: RetrofitServices
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: WeatherAdapter
    lateinit var dialog: AlertDialog
    lateinit var recyclerView: RecyclerView
    var weatherList: ArrayList<WeatherList> = arrayListOf()

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

        getWeatherList()
    }

    fun getWeatherList(){
        mService.getWeatherInfo().enqueue(object : Callback<WeatherInfo>{

            override fun onFailure(call: Call<WeatherInfo>, t: Throwable) {
                Log.v("Fail", t.toString())
            }

            override fun onResponse(call: Call<WeatherInfo>, response: Response<WeatherInfo>) {
                var wth = response.body() as WeatherInfo
                weatherList.addAll(wth.list)

                Log.v("Ok", wth.toString())
                adapter.notifyDataSetChanged()
            }

        })
    }

    override fun onCellClickListener(data: WeatherList) {
        var str: String = ""
    }
}

