package com.example.newactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide

class PicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pic_layout)

        title = "Картинка"
        val link = intent.getStringExtra("picLink")
        val imageView2: ImageView = findViewById(R.id.imageView2)
        Glide.with(this)
            .load(link)
            .into(imageView2)
    }

}
