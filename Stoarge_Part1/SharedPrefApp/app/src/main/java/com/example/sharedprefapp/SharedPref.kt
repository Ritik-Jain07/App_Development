package com.example.sharedprefapp

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {
    // Creating object of Shared Preference

    companion object{
        const val NAME = "name"
        const val SUBMIT = "submit"
    }
    val pref = context.getSharedPreferences("SharedPref",Context.MODE_PRIVATE)

    // function to insert value in shared preference
    fun insertValue(key:String,value: String){

        val editor:SharedPreferences.Editor= pref.edit()

        editor.putString(key, value)
        editor.apply()
    }
    // Overload of function is done
    fun insertValue(key: String,value: Boolean){
        val editor:SharedPreferences.Editor=pref.edit()

        editor.putBoolean(key,value)
        editor.apply()
    }

    fun getValue(key:String,defaultValue: String):String?{
        return pref.getString(key,defaultValue)
    }

    fun getValue(key: String,defaultValue: Boolean):Boolean?{
        return pref.getBoolean(key,defaultValue)
    }
}