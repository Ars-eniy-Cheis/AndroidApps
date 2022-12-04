package com.example.retrofitforecaster

import com.google.gson.annotations.SerializedName


data class Rain (

  @SerializedName("3h" ) var third_h : Double? = null

)