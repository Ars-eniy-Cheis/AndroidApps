package com.example.mydialer

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException


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

fun getContacts(contactsOriginal: ArrayList<Contact>, contacts: ArrayList<Contact>, search: String){
    contacts.clear()
    for(i in contactsOriginal.indices){
            if(contactsOriginal[i].name.contains(search, ignoreCase = true) or
                contactsOriginal[i].phone.contains(search, ignoreCase = true) or
                contactsOriginal[i].type.contains(search, ignoreCase = true)){
                contacts.add(contactsOriginal[i])
            }
        }
}


class MainActivity : AppCompatActivity(), CellClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonFileString = getJsonDataFromAsset(applicationContext, "phones.json")

        var mSettings: SharedPreferences = getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        val etSearch: TextView = findViewById(R.id.et_search)
        etSearch.setText(mSettings.getString("SEARCH_FILTER", ""))

        val gson = Gson()
        val listContactType = object : TypeToken<List<Contact>>() {}.type

        val contactsOriginal: ArrayList<Contact> = gson.fromJson(jsonFileString, listContactType)
        var contacts: ArrayList<Contact> = gson.fromJson(jsonFileString, listContactType)


        val recyclerView: RecyclerView = findViewById(R.id.rView)
        val adapter = ContactAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.submitList(contacts)

        etSearch.addTextChangedListener{
            val search: String = etSearch.text.toString()
            getContacts(contactsOriginal, contacts, search)
            adapter.notifyDataSetChanged()

            val editor: SharedPreferences.Editor = mSettings.edit()
            editor.putString("SEARCH_FILTER", search)
            editor.apply()
        }

    }

    override fun onCellClickListener(data: Contact) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:"+data.phone)
        startActivity(intent)
    }
}