package com.example.filestorage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val file = "file.tx"

        btnWrite.setOnClickListener(){
            var text:String = editText.text.toString()

            try{
                val fileOutput:FileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
                fileOutput.write(text?.toByteArray())
                fileOutput.close()
                Toast.makeText(this,"File Saved",Toast.LENGTH_SHORT).show()
            }catch (e:Exception){
                e.printStackTrace()
            }
        }


        btnRead.setOnClickListener(){
            try{
                val fin:FileInputStream = openFileInput(file)
                var c: Int
                var temp =""
                while (fin.read().also { c=it }!=-1){
                    temp += c.toChar().toString()
                }
                tv.text=temp
                Toast.makeText(this,"File Read Successfuuly",Toast.LENGTH_SHORT).show()
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}
