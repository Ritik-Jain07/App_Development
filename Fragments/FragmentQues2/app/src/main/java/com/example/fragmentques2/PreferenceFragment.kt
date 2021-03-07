package com.example.fragmentques2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class PreferenceFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference_fragment)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.preferenceFragmentContainer, MySettingsFragment())
            .commit()
    }
}