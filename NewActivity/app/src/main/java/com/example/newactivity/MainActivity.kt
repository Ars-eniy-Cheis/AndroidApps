package com.example.newactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnShowPic: Button = findViewById(R.id.btn_show_pic)
        btnShowPic.setOnClickListener{
            val intent = Intent(this, PicActivity::class.java)
            intent.putExtra("picLink", "https://gamestracker.org/_ld/12/1282.jpg")
            startActivity(intent)
            val picActivity = PicActivity()

        }

    }

}