package com.example.sharedprefapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.InputFilter
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRetrieve.setOnClickListener(View.OnClickListener {
            if(preference.getValue(SharedPref.SUBMIT,false)!!) {
                tv.text = preference.getValue(SharedPref.NAME, "")
            }
                        else{
                Toast.makeText(this,"First Enter the information",Toast.LENGTH_SHORT).show()
            }
        })



        btnSubmit.setOnClickListener(){
            if(name.text.toString().isBlank()){
                name.error = "Please enter name"
            }
            preference.insertValue(SharedPref.NAME,name.text.toString())
            preference.insertValue(SharedPref.SUBMIT,true)
        }
    }
}