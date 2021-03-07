package com.example.hetrogeneousandpagination

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.IllegalArgumentException

class CustomAdapter (private val context :Context , private val listView:List<Int>)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // companion object to identify view
    companion object{

        const val TEXT_VIEW = 0
        const val IMAGE_VIEW = 1
        const val TEXT_WITH_IMAGE = 2
        }

    class ViewHolderText(view: View):RecyclerView.ViewHolder(view){
        val textView : TextView = view.findViewById(R.id.text_view)

    }

    class ViewHolderImage(view: View):RecyclerView.ViewHolder(view){
        val imageView : ImageView = view.findViewById(R.id.image_view)
    }

    class ViewHolderImageText(view: View) : RecyclerView.ViewHolder(view){
        val image : ImageView = view.findViewById(R.id.image_text_view1)
        val text : TextView = view.findViewById(R.id.text_image_view1)
    }

    override fun getItemViewType(position: Int): Int=listView[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when(viewType){
            TEXT_VIEW -> ViewHolderText(inflater.inflate(R.layout.text_view,parent,false))
            IMAGE_VIEW -> ViewHolderImage(inflater.inflate(R.layout.image_view,parent,false))
            TEXT_WITH_IMAGE -> ViewHolderImageText(inflater.inflate(R.layout.text_image_view,parent,false))
            else -> throw IllegalArgumentException("No ViewHolder")
        }
    }
    override fun getItemCount(): Int=listView.size


     override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(listView[position]){
            TEXT_VIEW ->{
                val viewHolderText = holder as ViewHolderText
                viewHolderText.textView.text = "Paytm Karo"
            }
            IMAGE_VIEW ->{
                val viewHolderImage = holder as ViewHolderImage
                viewHolderImage.imageView.setImageResource(R.drawable.paytm)
            }
            TEXT_WITH_IMAGE -> {
                val viewHolderImageText = holder as ViewHolderImageText
                    viewHolderImageText.image.setImageResource(R.drawable.imgwithtext)
                    viewHolderImageText.text.text = "Get 10% Assure Cashback"
            }
        }
    }



}