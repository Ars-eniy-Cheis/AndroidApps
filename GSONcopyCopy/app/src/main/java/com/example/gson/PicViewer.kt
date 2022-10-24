package com.example.gson

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

class PicViewer : AppCompatActivity() {
    var link: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pic_viewer)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        title = "Картинка"
        link = intent.getStringExtra("picLink").toString()
        val imageView: ImageView = findViewById(R.id.imageView)
        Glide.with(this)
            .load(link)
            .into(imageView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val maimIntent = Intent()
        maimIntent.putExtra("picLink", link)
        maimIntent.putExtra("message", "Картинка добавлена в избранное")
        setResult(RESULT_OK, maimIntent)
        finish()
        return super.onOptionsItemSelected(item)
    }



}