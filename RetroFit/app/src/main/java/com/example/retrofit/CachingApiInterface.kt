package com.example.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST


interface CachingApiInterface {

    @GET("v2/posts.json")
    fun getPost():Call<CachingModelResponse>

}
