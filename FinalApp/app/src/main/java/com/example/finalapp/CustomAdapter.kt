package com.example.finalapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalapp.model.DataModel
import com.example.finalapp.model.NewsModel

class CustomAdapter(private val context: Context, private var list: List<NewsModel>):
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    var newsList: List<DataModel>

    init {
        newsList = ArrayList()
    }

    fun addData(arrList: List<DataModel>){
        this.newsList = arrList
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image: ImageView = view.findViewById(R.id.imageView)
        val title: TextView = view.findViewById(R.id.textView)
        val published:TextView=view.findViewById(R.id.textView2)
        val source: TextView = view.findViewById(R.id.textView1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.source.text = list[position].source
        holder.published.text=list[position].published
        val img = list[position].image
        Glide.with(context).load(img
        ).placeholder(R.drawable.ic_launcher_foreground).into(holder.image)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}