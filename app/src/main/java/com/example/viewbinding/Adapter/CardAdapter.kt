package com.example.viewbinding.Adapter

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.TextView


import androidx.recyclerview.widget.RecyclerView
import com.example.viewbinding.Item.CardItem
import com.example.viewbinding.R


class CardAdapter(private val items: List<CardItem>, private val listener: OnItemClickListener) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: CardItem)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(items[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.titleTextView.text = item.title
        holder.descriptionTextView.text = item.des
    }

    override fun getItemCount(): Int {
        return items.size
    }
}