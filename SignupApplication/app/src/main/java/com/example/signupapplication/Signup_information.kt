package com.example.signupapplication

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class Signup_information : AppCompatActivity() {

    private val camera = 1000
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_information2)

        val name= findViewById<TextView>(R.id.Name)
        val phone= findViewById<TextView>(R.id.Phone)
        val email= findViewById<TextView>(R.id.Email)
        val password= findViewById<TextView>(R.id.Password)

        val get_name  = intent.getStringExtra("name")
        val get_phone = intent.getStringExtra("phone")
        val get_email = intent.getStringExtra("email")
        val get_password = intent.getStringExtra("password")

        name.setText(get_name)
        phone.setText(get_phone)
        email.setText(get_email)
        password.setText(get_password)

        val url_button = findViewById<Button>(R.id.URL_button)
        val permission_button = findViewById<Button>(R.id.Permission)
        val url_link = findViewById<EditText>(R.id.URL)

        url_button.setOnClickListener(View.OnClickListener{
            if (url_link.text.toString().isBlank()) {
                url_link.error = "Enter url"
            }
            else{
                val url: String = url_link.text.toString()
                val i = Intent(Intent.ACTION_VIEW)
                i.setData(Uri.parse(url))
                startActivity(i)

            }
        })

        permission_button.setOnClickListener(View.OnClickListener{
            when (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)){
                PackageManager.PERMISSION_GRANTED -> {
                    Log.e("USER_PERMISSION", "PERMISSION_GRANTED")
                    Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show()
                }
                PackageManager.PERMISSION_DENIED -> {
                    ActivityCompat.requestPermissions(this,
                        arrayOf(android.Manifest.permission.CAMERA),
                        camera)
                }
            }
        }
        )



    }
}