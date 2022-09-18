package com.example.nestedlayouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.allViews
import androidx.core.view.children
import kotlin.reflect.KMutableProperty0

var text_value = 1

fun clear (view_array: Array<TextView>){
    for (i in view_array){
        i.text = ""
    }
}

fun setTextView (view_array: Array<TextView>, value: String){
    for (i in view_array){
        i.text = value
    }
}

fun change (view_array_current: Array<TextView>, view_array_next: Array<TextView>){
    clear(view_array_current)
    text_value += 1
    setTextView(view_array_next, text_value.toString())
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //var main_view: LinearLayout = findViewById(R.id.main)
        //var main_views_collection: Sequence<View> = main_view.children


        var text_views1: Array<TextView> = arrayOf(findViewById<TextView>(R.id.textView),
            findViewById<TextView>(R.id.textView4), findViewById<TextView>(R.id.textView7))

        var text_views2: Array<TextView> = arrayOf(findViewById<TextView>(R.id.textView2),
            findViewById<TextView>(R.id.textView5), findViewById<TextView>(R.id.textView8))

        var text_views3: Array<TextView> = arrayOf(findViewById<TextView>(R.id.textView3),
            findViewById<TextView>(R.id.textView6), findViewById<TextView>(R.id.textView9))

        val roll_button: Button = findViewById<Button>(R.id.roll)
        roll_button.setOnClickListener{
            //Можно передавать ссылки на глобальные объекты, но нельзя на локальные (??????)
            if (text_value.mod(3) == 1){
                change(text_views1, text_views2)

            }
            else if (text_value.mod(3) == 2){
                change(text_views2, text_views3)

            }
            else{
                change(text_views3, text_views1)
            }
        }
    }
}