package com.example.fragques1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.FragQues1.BlankFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
// .add
        supportFragmentManager.beginTransaction().add(R.id.frame, BlankFragment()).commit()
        Log.i("MainActivity---->","OnCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity---->","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainActivity---->","onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivity---->","onRestart")

    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActivity---->","onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity---->","onDestroy")

    }
}
