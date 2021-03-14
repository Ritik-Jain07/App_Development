package com.example.threads

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BatteryReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val percentage = intent?.getIntExtra("level", 0)
        if(percentage != 0){
            Toast.makeText(context, "Batter percentage " + percentage.toString() + "%", Toast.LENGTH_SHORT).show()
        }
    }


}
