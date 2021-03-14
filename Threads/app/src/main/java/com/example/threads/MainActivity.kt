package com.example.threads

import android.content.*
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val batteryReceiver:BroadcastReceiver = BatteryReceiver()
    var myService: BoundService? = null
    var isBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Use start() join() and sleep() methods in single program to execute different threads

        threadDemo()


        //Register receiver  for incoming calls and battery status.
        receiverDemoBtn.setOnClickListener() {
            receiverDemo()
        }

        //Music Player with raw file to play song in background.

        serviceDemo()

        //Bind local service from activity.

        // To use local service first we have to bind it
        bindLocalService()
        // then get that service
        localServiceDemoBtn.setOnClickListener {
            // get current time using local service
            showTime()
        }

    }


    private fun threadDemo(){
        val thread= MyThread(this)
        val thread1= MyThread1(this)

        thread.start()
        thread1.start()
        thread.join()
    }

    private fun receiverDemo(){
       val  batteryReceiver = BatteryReceiver()
        // Dynamic Registration of broadcast receiver
        registerReceiver(batteryReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_PHONE_STATE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_PHONE_STATE), 1
            )
        }
    }

    private fun serviceDemo() {
        val serviceIntent = Intent(applicationContext, MusicService::class.java)
        serviceStartDemoBtn.setOnClickListener {
            startService(serviceIntent)
        }
        serviceStopDemoBtn.setOnClickListener {
            stopService(serviceIntent)
        }
    }

    private fun bindLocalService() {
        val intent = Intent(this, BoundService::class.java)
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE)
    }

    private fun showTime() {
        val currentTime = myService?.getCurrentTime()
        Toast.makeText(this, "Current time is: ${currentTime.toString()}", Toast.LENGTH_SHORT)
            .show()
    }

    private val myConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as BoundService.MyLocalBinder
            myService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
        }
    }

    override fun onStop() {
        super.onStop()

        // Unregister the Receiver
        // when activity is not visible
        unregisterReceiver(batteryReceiver)
    }
}