package com.example.fragmentques2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.preference.PreferenceFragmentCompat

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentTransaction: FragmentTransaction
    private lateinit var fragmentManager: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnadd = findViewById<Button>(R.id.btnadd)
        val btnreplace = findViewById<Button>(R.id.btnreplace)
        val btnhide = findViewById<Button>(R.id.btnhide)
        val btnshow = findViewById<Button>(R.id.btnshow)
        val frag1=Fragment1()
        val frag2=Fragment2()
        btnadd.setOnClickListener() {
            supportFragmentManager.beginTransaction().add(R.id.container, frag1).commit()
        }
        btnreplace.setOnClickListener() {
            supportFragmentManager.beginTransaction().replace(R.id.container, frag2).commit()
        }
        btnhide.setOnClickListener() {
            if(frag1.isAdded) {
                supportFragmentManager.beginTransaction().hide(frag1).commit()
            }
            else if(frag2.isAdded){
          supportFragmentManager.beginTransaction().hide(frag2).commit()
            }
        else{
            Toast.makeText(this,"Nothing to hide please add first",Toast.LENGTH_LONG).show()
        }}

        btnshow.setOnClickListener() {
            if(frag1.isHidden)
            {
                supportFragmentManager.beginTransaction().show(frag1).commit()
            }
            else if(frag2.isHidden)
            {
                supportFragmentManager.beginTransaction().show(frag2).commit()
            }
        }

        val btnremove = findViewById<Button>(R.id.btnremove)
        btnremove.setOnClickListener() {
            if(frag1.isAdded)
            {
                supportFragmentManager.beginTransaction().remove(frag1).commit()
            }
            else if(frag2.isAdded)
            {
                supportFragmentManager.beginTransaction().remove(frag2).commit()

            }
        }

        val btnpref = findViewById<Button>(R.id.btnpref)
        btnpref.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, PreferenceFragment::class.java)
            startActivity(intent)
        })

        // Dialog Fragment
        val btndialog = findViewById<Button>(R.id.btndialog)

        supportFragmentManager.beginTransaction().show(DialogFragment()).commit()
        fragmentManager = supportFragmentManager
        btndialog.setOnClickListener(View.OnClickListener {
            DialogFragment().show(fragmentManager, "huh")
        })




}}

class MySettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference, rootKey)
    }
}
