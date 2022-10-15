package com.example.mydialer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ContactAdapter (private val context: Context,
                      private val list: ArrayList<Contact>) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textName: TextView = view.findViewById(R.id.textName)
        val textPhone: TextView = view.findViewById(R.id.textPhone)
        val textType: TextView = view.findViewById(R.id.textType)

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
        holder.textName.setText(data.name)
        holder.textPhone.setText(data.phone)
        holder.textType.setText(data.type)
    }
}