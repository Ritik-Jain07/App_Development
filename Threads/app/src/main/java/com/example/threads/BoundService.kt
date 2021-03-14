package com.example.threads

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.text.SimpleDateFormat
import java.util.*

class BoundService: Service() {

    private val myBinder = MyLocalBinder()

    fun getCurrentTime(): String {
        val dateFormat = SimpleDateFormat("HH:mm:ss MM/dd/yyyy",
            Locale.US)
        return dateFormat.format(Date())
    }

    class MyLocalBinder: Binder() {
        fun getService(): BoundService {
            return BoundService()
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return myBinder
    }

}
