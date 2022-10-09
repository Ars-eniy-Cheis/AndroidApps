package com.example.gson

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PhotoAdapter (private val context: Context,
                    private val list: ArrayList<String>,
                    private val cellClickListener: CellClickListener) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val catImage: ImageView = view.findViewById(R.id.catImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rview_item,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        Glide.with(context)
            .load(data)
            .into(holder.catImage)

        holder.itemView.setOnClickListener{
            cellClickListener.onCellClickListener(data)
        }
    }
}