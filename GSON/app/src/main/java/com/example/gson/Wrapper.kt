package com.example.gson

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import org.json.JSONObject

data class Wrapper(
    val photos: JsonObject,
    val stat: String

)
