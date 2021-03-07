package com.example.fragmentques4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fm = supportFragmentManager
        val button1=findViewById<Button>(R.id.button1)
        val button2=findViewById<Button>(R.id.button2)
        val button3=findViewById<Button>(R.id.button3)
        val button4=findViewById<Button>(R.id.button4)

        button1.setOnClickListener(){

            fm.beginTransaction().replace(R.id.fragment, Fragment1(),"f").commit()


        }
        button2.setOnClickListener(){
            fm.beginTransaction().replace(R.id.fragment, Fragment2(),"r").commit()

        }
        button3.setOnClickListener(){
            fm.beginTransaction().replace(R.id.fragment, Fragment3(),"m").commit()
        }
        button4.setOnClickListener(){
            fm.beginTransaction().replace(R.id.fragment, Fragment4(),"m").commit()
        }
    }
}