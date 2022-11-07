package com.example.retrofitforecaster

import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {

    @GET("forecast?q=Kemerovo&appid=bf59f6cb20b49d9248c8fabd9022a087&units=metric")

    fun getWeatherInfo(): Call<WeatherInfo>
}