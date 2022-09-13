package com.example.attributes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edit: EditText = findViewById<EditText>(R.id.editText)

        val button1: Button = findViewById<Button>(R.id.button1)
        button1.setOnClickListener{
            edit.setTextColor(ContextCompat.getColor(this,R.color.white))
        }

        val button2: Button = findViewById<Button>(R.id.button2)
        button2.setOnClickListener{
            edit.setTextColor(ContextCompat.getColor(this, R.color.red))
        }

        val button3: Button = findViewById<Button>(R.id.button3)
        button3.setOnClickListener{
            edit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 8F)
        }

        val button4: Button = findViewById<Button>(R.id.button4)
        button4.setOnClickListener{
            edit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24F)
        }

        val button5: Button = findViewById<Button>(R.id.button5)
        button5.setOnClickListener{
            edit.setBackgroundColor(ContextCompat.getColor(this,R.color.black))
        }

        val button6: Button = findViewById<Button>(R.id.button6)
        button6.setOnClickListener{
            edit.setBackgroundColor(ContextCompat.getColor(this,R.color.yellow))
        }
    }
}