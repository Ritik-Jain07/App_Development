package com.example.finalapp

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.finalapp.model.DataModel
import com.example.finalapp.model.NewsModel
import com.example.finalapp.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Response

class MyViewModel: ViewModel() {

    var newsRepo: NewsRepo? = null
    var mutableLiveData: MutableLiveData<List<DataModel>>? = null

    init {
        newsRepo = NewsRepo()
    }

    fun loadData(): LiveData<List<DataModel>> {
        if (mutableLiveData == null) {
            mutableLiveData = newsRepo!!.getData()
        }
        return mutableLiveData!!
    }

}