package com.example.complexevent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var progress = 0

        val checkBox: CheckBox = findViewById(R.id.save_checkBox)

        val editText: EditText = findViewById(R.id.editText)

        val textView: TextView = findViewById(R.id.textView)

        val progressBar: ProgressBar = findViewById(R.id.progressBar)

        val button: Button = findViewById<Button>(R.id.save_button)
        button.setOnClickListener{
            if(checkBox.isChecked){
                progress += 10
                textView.setText(editText.text.toString())
                progressBar.setProgress(progress, true)
            }
        }
    }
}