package com.example.retrofitforecaster

import retrofit2.create

object Common {

    private val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create<RetrofitServices>()
}