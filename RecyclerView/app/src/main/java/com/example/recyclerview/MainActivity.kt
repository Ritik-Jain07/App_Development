package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myArrayList = ArrayList<Model>()
        myArrayList.add(Model(R.drawable.food2, "Ice Cream Sundae", "145 Mall of India", "520 reviews", "01 Mar 2019 11:30 AM", "Rs. 80"))
        myArrayList.add(Model(R.drawable.food2, "Ice Cream Sundae", "145 Mall of India", "520 reviews", "01 Mar 2019 11:30 AM", "Rs. 80"))
        myArrayList.add(Model(R.drawable.food2, "Ice Cream Sundae", "145 Mall of India", "520 reviews", "01 Mar 2019 11:30 AM", "Rs. 80"))
        myArrayList.add(Model(R.drawable.food2, "Ice Cream Sundae", "145 Mall of India", "520 reviews", "01 Mar 2019 11:30 AM", "Rs. 80"))
        myArrayList.add(Model(R.drawable.food2, "Ice Cream Sundae", "145 Mall of India", "520 reviews", "01 Mar 2019 11:30 AM", "Rs. 80"))
        myArrayList.add(Model(R.drawable.food2, "Ice Cream Sundae", "145 Mall of India", "520 reviews", "01 Mar 2019 11:30 AM", "Rs. 80"))
        myArrayList.add(Model(R.drawable.food2, "Ice Cream Sundae", "145 Mall of India", "520 reviews", "01 Mar 2019 11:30 AM", "Rs. 80"))
        myArrayList.add(Model(R.drawable.food2, "Ice Cream Sundae", "145 Mall of India", "520 reviews", "01 Mar 2019 11:30 AM", "Rs. 80"))
        myArrayList.add(Model(R.drawable.food2, "Ice Cream Sundae", "145 Mall of India", "520 reviews", "01 Mar 2019 11:30 AM", "Rs. 80"))
        myArrayList.add(Model(R.drawable.food2, "Ice Cream Sundae", "145 Mall of India", "520 reviews", "01 Mar 2019 11:30 AM", "Rs. 80"))


        val recycle=findViewById<RecyclerView>(R.id.rv)
        recycle.adapter=CustomAdapter(myArrayList)
        recycle.layoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL,false)
    }
}