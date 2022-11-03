package com.example.mydialer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val textName: TextView = view.findViewById(R.id.textName)
    val textPhone: TextView = view.findViewById(R.id.textPhone)
    val textType: TextView = view.findViewById(R.id.textType)

    fun bindTo(contact: Contact){
        textName.text = contact.name
        textPhone.text = contact.phone
        textType.text = contact.type
    }

}

class ContactAdapter(private val cellClickListener: CellClickListener): ListAdapter<Contact, ViewHolder>(ContactDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rview_item,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = currentList[position]
        holder.bindTo(data)

        holder.itemView.setOnClickListener{
            cellClickListener.onCellClickListener(data)
        }
    }


}