package com.example.toasthandler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import timber.log.Timber;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Timber.plant(Timber.DebugTree())
        var edit: EditText

        val buttonTimber: Button = findViewById<Button>(R.id.button_timber)
        buttonTimber.setOnClickListener {
            edit = findViewById<EditText>(R.id.editTextTextPersonName)
            Timber.v(edit.text.toString())
        }

        val button: Button = findViewById<Button>(R.id.button_log)
        button.setOnClickListener{
            val tag: String = "From EditText"
            edit = findViewById<EditText>(R.id.editTextTextPersonName)
            Log.v(tag, edit.text.toString())
        }

    }
}