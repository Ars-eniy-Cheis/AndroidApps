package com.example.internettest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import okhttp3.*
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.io.IOException

fun catFacts(url: String, tag: String, client: OkHttpClient){
    val request : Request = Request.Builder().url(url).build()
    client.newCall(request).enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {
            //
        }

        override fun onResponse(call: Call, response: Response) {
            val str = response.body()?.string().toString()
            Log.v(tag, str)

        }
    })
}


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tag : String = "Flickr cats"
        val tag2 : String = "Flickr OkCats"

        val myURL= "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1"

        val btnHTTP : Button = findViewById(R.id.btnHTTP)
        btnHTTP.setOnClickListener{
            Thread {
                val myConnection = URL(myURL).openConnection()
                        as HttpURLConnection
                try{
                    val data = myConnection.inputStream.bufferedReader().readText()
                    myConnection.disconnect()
                    Log.v(tag, data)
                }
                catch (e: Exception){
                    e.printStackTrace()
                }

            }.start()

        }

        val btnOkHTTP : Button = findViewById(R.id.btnOkHTTP)
        var okHttpClient: OkHttpClient = OkHttpClient()
        btnOkHTTP.setOnClickListener{
            catFacts(myURL, tag2, okHttpClient)
        }
    }
}