package com.example.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.button)
        val btn2 = findViewById<Button>(R.id.button2)
        val btn3 = findViewById<Button>(R.id.button3)
        btn1.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, LinearLayoutActivity::class.java)
            startActivity(intent)
        })

        btn2.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, RelativeLayoutActivity::class.java)
            startActivity(intent)
        })

        btn3.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, ConstraintLayoutActivity::class.java)
            startActivity(intent)
        })
    }
}