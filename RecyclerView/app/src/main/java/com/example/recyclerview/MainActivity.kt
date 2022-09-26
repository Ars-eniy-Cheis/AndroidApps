package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), CellClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ColorAdapter(this, fetchList(), this)
    }

    private fun fetchList(): ArrayList<ColorData> {
        val list = arrayListOf<ColorData>()

        val colorData1 = ColorData("red", resources.getColor(R.color.red))
        val colorData2 = ColorData("yellow", resources.getColor(R.color.yellow))
        val colorData3 = ColorData("purple", resources.getColor(R.color.purple_500))
        val colorData4 = ColorData("white", resources.getColor(R.color.white))
        val colorData5 = ColorData("teal", resources.getColor(R.color.teal_200))

        list.add(colorData1)
        list.add(colorData2)
        list.add(colorData3)
        list.add(colorData4)
        list.add(colorData5)
        list.add(colorData1)
        list.add(colorData2)
        list.add(colorData3)
        list.add(colorData4)
        list.add(colorData5)
        list.add(colorData1)
        list.add(colorData2)
        list.add(colorData3)
        list.add(colorData4)
        list.add(colorData5)

        return list
    }

    override fun onCellClickListener(data: ColorData) {
        Toast.makeText(this,"IT'S " + data.colorName, Toast.LENGTH_SHORT).show()
    }
}