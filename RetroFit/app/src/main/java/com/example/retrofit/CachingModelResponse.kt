package com.example.retrofit

import com.google.gson.annotations.SerializedName

data class CachingModelResponse(
    @SerializedName("posts")
    val posts: ArrayList<CachingDataModel>
    )