package com.example.architecture

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.architecture.mvp.mvpactivity.MvpMainActivity
import com.example.architecture.mvvm.MvvmMainActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mvmActivity.setOnClickListener {
            val intent = Intent(this, MvpMainActivity::class.java)
            startActivity(intent)
        }
        mvvmActivity.setOnClickListener {
            val intent = Intent(this, MvvmMainActivity::class.java)
            startActivity(intent)
        }
    }
}