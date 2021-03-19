package com.example.retrofit

import com.google.gson.annotations.SerializedName
import retrofit2.http.Url

data class CachingDataModel(

    @SerializedName("name")
    val name: String,
    @SerializedName("message")
    val message : String,
    @SerializedName("image")
    val image:String

)
