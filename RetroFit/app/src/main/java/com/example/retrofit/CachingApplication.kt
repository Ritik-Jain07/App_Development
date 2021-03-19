package com.example.retrofit

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class CachingApplication: Application() {


    override fun onCreate() {
        super.onCreate()
        if (instance == null) {
            instance = this
        }
    }


    /**
     * Function to check for network connectivity
     * @param -> context
     * @return -> Boolean value
     */


    private fun isNetworkConnected(): Boolean {
        val cm =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting
    }

    companion object {
        var instance: CachingApplication? = null
        fun hasNetwork(): Boolean {
            return instance?.isNetworkConnected()?:false
        }
    }

}