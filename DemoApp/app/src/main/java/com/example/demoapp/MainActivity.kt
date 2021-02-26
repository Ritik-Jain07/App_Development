package com.example.demoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val submit = findViewById<Button>(R.id.submit)
        submit.setOnClickListener {
            val intent = Intent(this@MainActivity, WelcomeScreen::class.java)
            startActivity(intent)


        }
    }

}