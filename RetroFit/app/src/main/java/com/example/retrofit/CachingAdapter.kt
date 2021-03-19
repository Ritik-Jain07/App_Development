package com.example.retrofit

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CachingAdapter(private val context: Context, private val dataSet: List<CachingDataModel>):
    RecyclerView.Adapter<CachingAdapter.ViewHolder>(){


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val postAuthorTextView: TextView = view.findViewById(R.id.name)
        val postMsgTextView: TextView = view.findViewById(R.id.message)
        val postImageView: ImageView = view.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CachingAdapter.ViewHolder {

        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    // Replace the contents of a view (invoked by the layout manager)
    // Used to replace/update views at a specific position
    override fun onBindViewHolder(holder: CachingAdapter.ViewHolder, position: Int) {
        holder.postAuthorTextView.text = dataSet[position].name
        holder.postMsgTextView.text = dataSet[position].message

        val profileImgURL = dataSet[position].image
        Glide.with(context).load("https://moodle.fhgr.ch/pluginfile.php/124614/mod_page/content/4/example.jpg").placeholder(R.mipmap.ic_launcher)
            .into(holder.postImageView)

//        Glide.with(context)
//            .load("https://moodle.fhgr.ch/pluginfile.php/124614/mod_page/content/4/example.jpg")
//            .placeholder(R.mipmap.ic_launcher)
//            .into(holder.postImageView)

    }
}