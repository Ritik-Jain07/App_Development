package com.example.retrofit

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_caching.*
import retrofit2.Response

class CachingActivity : AppCompatActivity() {

    lateinit var progressDialog: ProgressDialog
    var dataList = ArrayList<CachingDataModel>()
    private lateinit var customAdapter: CachingAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caching)


        createProgressDialog()
        setupRecyclerView()

        getPostsData()
    }


    // Create progress dialog
    private fun createProgressDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Please wait while we are fetching post...")
        progressDialog.setCancelable(false)
    }

    // Setup the recycler view
    // and attach adapter to it
    private fun setupRecyclerView() {
        // Set layout for RecyclerView
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager

        customAdapter = CachingAdapter(this@CachingActivity, dataList)
        // attach adapter
        recyclerView.adapter = customAdapter
    }

    private fun getPostsData() {
        progressDialog.show()

        val call = CachingApiClient.getClient.getPost()
        call.enqueue(object : retrofit2.Callback<CachingModelResponse>{

            override fun onFailure(call: retrofit2.Call<CachingModelResponse>, t: Throwable) {
                Log.d("MainActivity", "Error is ${t.message}")
                Toast.makeText(this@CachingActivity, "There is some error while getting post", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()
            }

            override fun onResponse(
                    call: retrofit2.Call<CachingModelResponse>,
                    response: Response<CachingModelResponse>
            ) {
                dataList.addAll(response.body()?.posts?:ArrayList())
                recyclerView.adapter?.notifyDataSetChanged()
                progressDialog.dismiss()
                Log.d("MainActivity", "Data is ${response.body()}")

            }

        })
    }
}