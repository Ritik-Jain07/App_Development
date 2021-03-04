package com.example.signupapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val name= findViewById<EditText>(R.id.Name)
        val email=findViewById<EditText>(R.id.EmailAddress)
        val phone=findViewById<EditText>(R.id.Phone)
        val password=findViewById<EditText>(R.id.Password)
        val signup = findViewById<Button>(R.id.button)
        signup.setOnClickListener {
            if(name.text.toString().isBlank()){
                name.error = "Please enter name"
            }
            else if(email.text.toString().isBlank()){
                email.error = "Please enter email"
            }
            else if(phone.text.toString().isBlank()){
                phone.error = "Please enter phone number"
            }
            else if(password.text.toString().isBlank()){
                password.error = "Please enter name"
            }else {
                val intent = Intent(this@MainActivity, HomePage::class.java)
                intent.putExtra("name", name.text.toString())
                intent.putExtra("email", email.text.toString())
                intent.putExtra("phone", phone.text.toString())
                intent.putExtra("password", password.text.toString())
                startActivity(intent)
            }
    }
}
}