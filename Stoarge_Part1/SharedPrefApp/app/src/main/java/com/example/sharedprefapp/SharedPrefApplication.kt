package com.example.sharedprefapp

import android.app.Application
import android.content.SharedPreferences


val preference:SharedPref by lazy {
    SharedPrefApplication.pref!!
}

class SharedPrefApplication:Application() {

    companion object{
        var pref: SharedPref? = null
    }

    override fun onCreate() {
        super.onCreate()

        // Creating an object of SharedPref class
        pref = SharedPref(this)
    }

}