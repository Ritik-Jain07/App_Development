package com.example.Coroutine.model

import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("id")
    val postID: Int,
    @SerializedName("title")
    val postTitle: String,
    @SerializedName("body")
    val postBody: String
)