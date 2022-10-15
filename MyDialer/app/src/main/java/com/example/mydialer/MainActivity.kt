package com.example.mydialer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import timber.log.Timber
import timber.log.Timber.Forest.plant
import android.content.Context
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}

fun getContacts(service: ExecutorService, contacts: ArrayList<Contact>, search: String): ArrayList<Contact>{
    val contact = service.submit(Callable<ArrayList<Contact>>{
        var searchContact: ArrayList<Contact> = arrayListOf()
        for(i in contacts.indices){
            if(contacts[i].name.contains(search, ignoreCase = true) or
                contacts[i].phone.contains(search, ignoreCase = true) or
                contacts[i].type.contains(search, ignoreCase = true)){
                searchContact.add(contacts[i])
            }
        }
        searchContact
    })


    return contact.get()
}


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        plant(Timber.DebugTree())

        val jsonFileString = getJsonDataFromAsset(applicationContext, "phones.json")
        Timber.v(jsonFileString)

        val gson = Gson()
        val listContactType = object : TypeToken<List<Contact>>() {}.type

        var contacts: ArrayList<Contact> = gson.fromJson(jsonFileString, listContactType)
        for(i in contacts.indices){
            Timber.v(contacts[i].toString())
        }

        val recyclerView: RecyclerView = findViewById(R.id.rView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ContactAdapter(this, contacts)

        val etSearch: TextView = findViewById(R.id.et_search)
        val service: ExecutorService = Executors.newFixedThreadPool(2)
        val btnSearch: Button = findViewById(R.id.btn_search)
        btnSearch.setOnClickListener{
            val search: String = etSearch.text.toString()
            var searchContact: ArrayList<Contact> = getContacts(service, contacts, search)

            if(searchContact.size > 0){
                recyclerView.adapter = ContactAdapter(this, searchContact)
            }
            else{
                recyclerView.adapter = ContactAdapter(this, contacts)
            }
        }

    }
}