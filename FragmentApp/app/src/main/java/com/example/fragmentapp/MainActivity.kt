package com.example.fragmentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    private lateinit var manager: FragmentManager
    private lateinit var transaction: FragmentTransaction
    val listView = ListView()
    val detailView = DeatilsView()
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("Activity", "onCreate is called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }}