package com.example.hetrogeneousandpagination

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var startPage = 1
    private var isLoading = false
    private val limit = 4
    private lateinit var recyclerView: RecyclerView
    private lateinit var customAdapter: CustomAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private val list= mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView1)
        layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager

        getPage()

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if (dy > 0) {
                    val visibleItem = layoutManager.childCount
                    val totalItem = layoutManager.itemCount
                    val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                    if (!isLoading) {
                        if ((visibleItem + firstVisibleItem) >= totalItem) {
                            startPage++
                            getPage()
                        }
                    }
                }
                super.onScrolled(recyclerView, dx, dy)

            }
        })
    }

    private fun getPage(){
        isLoading = true

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.VISIBLE

        val start = (startPage-1)*limit
        val end = startPage * limit

        for (i in start..end){
            when{
                i%2 ==0 ->{
                    list.add(CustomAdapter.TEXT_VIEW)
                }
                i%3 ==0 ->{
                    list.add(CustomAdapter.IMAGE_VIEW)
                }
                else -> {
                    list.add((CustomAdapter.TEXT_WITH_IMAGE))
                }
            }
        }

        Handler().postDelayed(
            {
                if(::customAdapter.isInitialized){
                    customAdapter.notifyDataSetChanged()
                }
                else{
                    customAdapter= CustomAdapter(this,list)
                    recyclerView.adapter = customAdapter
                }
                isLoading = false
                progressBar.visibility = View.GONE
            }, 2000
        )
    }
}