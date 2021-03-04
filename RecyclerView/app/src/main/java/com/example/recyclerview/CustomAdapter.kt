package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(val list:ArrayList<Model>): RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.img)
        val tvname = itemView.findViewById<TextView>(R.id.tvname)
        val tvaddress = itemView.findViewById<TextView>(R.id.tvaddress)
        val tvreviews = itemView.findViewById<TextView>(R.id.tvreviews)
        val tvtime = itemView.findViewById<TextView>(R.id.tvtime)
        val tvprice = itemView.findViewById<TextView>(R.id.tvprice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.img.setImageResource(list[position].img)
        holder.tvname.text = list[position].name
        holder.tvaddress.text = list[position].address
        holder.tvreviews.text = list[position].reviews
        holder.tvtime.text = list[position].time
        holder.tvprice.text = list[position].price
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
