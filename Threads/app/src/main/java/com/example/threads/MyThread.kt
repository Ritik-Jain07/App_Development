package com.example.threads

import android.content.Context
import android.util.Log
import android.widget.Toast
import java.lang.Exception

class MyThread(private val context: Context):Thread() {

    val TAG ="MyThread"

    override fun run() {
        super.run()
        Log.i(TAG, " is running")

        try {
            Thread.sleep(500)
        }
        catch (e: Exception) {
            Toast.makeText(context, "Exception: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
        }
    }
}