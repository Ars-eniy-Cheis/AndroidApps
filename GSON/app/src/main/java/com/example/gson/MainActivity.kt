package com.example.gson

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import timber.log.Timber
import timber.log.Timber.Forest.plant
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

fun getLinks(service: ExecutorService, URL: String): Array<String>{
    val linksArray = service.submit(Callable<Array<String>>{
        var links: Array<String> = emptyArray<String>()
        val myConnection = URL(URL).openConnection()
                as HttpURLConnection
        val data = myConnection.inputStream.bufferedReader().readText()
        myConnection.disconnect()
        val wrapper: Wrapper = Gson().fromJson(data, Wrapper::class.java)
        val page: PhotoPage = Gson().fromJson(wrapper.photos, PhotoPage::class.java)
        val photos: List<Photo> = Gson().fromJson(page.photo, Array<Photo>::class.java).toList()
        for (i in photos.indices step 5){
            Timber.v(photos[i].id + " " + photos[i].owner + " " + photos[i].title)
            links += "https://farm${photos[i].farm}.staticflickr.com/${photos[i].server}/${photos[i].id}_${photos[i].secret}_z.jpg"
        }
        links
    })
    return linksArray.get()
}

class MainActivity : AppCompatActivity(), CellClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plant(Timber.DebugTree())
        val URL: String = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1"
        val btnJSON: Button = findViewById(R.id.JSONbutton)
        val service: ExecutorService = Executors.newFixedThreadPool(2)

        btnJSON.setOnClickListener{
            val links: Array<String> = getLinks(service, URL)
            val recyclerView: RecyclerView = findViewById(R.id.rView)
            recyclerView.layoutManager = GridLayoutManager(this, 2)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = PhotoAdapter(this, arrayListOf<String>(*links), this)
        }
    }

    override fun onCellClickListener(data: String) {
        Timber.i(data)
        val clipboard: ClipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clip: ClipData = ClipData.newPlainText("link", data)
        clipboard.setPrimaryClip(clip)
    }

}