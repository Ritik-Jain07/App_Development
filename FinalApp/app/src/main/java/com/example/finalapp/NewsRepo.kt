package com.example.finalapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.finalapp.model.DataModel
import com.example.finalapp.retrofit.ApiClient
import com.example.finalapp.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepo {

    val TAG = javaClass.simpleName

    fun getData(): MutableLiveData<List<DataModel>> {
        var mutableList: MutableLiveData<List<DataModel>> = MutableLiveData()

        val apiInterface = ApiClient.getClient().create(ApiInterface::class.java)



        apiInterface.getNews().enqueue(object: Callback<List<DataModel>> {

            override fun onResponse(call: Call<List<DataModel>>, response: Response<List<DataModel>>) {
                Log.e(TAG, "onResponse response="+response.toString() )

                if(response.isSuccessful){
                    Log.e(TAG, "onResponse response.size="+response.body()?.size )

                    if(response.body()!=null && response.body()?.size!!>0 ) {
                        mutableList.value = response.body()!!
                    }
                }
            }

            override fun onFailure(call: Call<List<DataModel>>, t: Throwable) {
                Log.e(TAG, "onFailure call="+call.toString() )
            }

        })

        return mutableList
    }
}