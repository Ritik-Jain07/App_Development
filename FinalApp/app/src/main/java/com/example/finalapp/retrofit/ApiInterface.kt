package com.example.finalapp.retrofit

import com.example.finalapp.model.DataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("news")
    fun getNews(
        @Query("access_key")key:String,
        @Query("languages") language:String,
        @Query("countries")countries:String
    ): Call<DataModel>
}