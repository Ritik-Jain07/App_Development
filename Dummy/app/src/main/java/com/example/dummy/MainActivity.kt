package com.example.dummy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.perf.FirebasePerformance
import com.google.firebase.perf.metrics.AddTrace
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    private lateinit var firebaseAnalytics: FirebaseAnalytics

    val myTrace= FirebasePerformance.getInstance().newTrace("testTrace")
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val imageURL = getCustomDataFromFCM()
    if (imageURL != null) {
        updateUI(imageURL)
    } else {
        Toast.makeText(this, "URL in notification is not correct. Please check it again", Toast.LENGTH_SHORT)
            .show()
    }
}


    private fun updateUI(imageURL: String) {
        Glide
            .with(this)
            .load(imageURL)
            .into(imageView)
    }

    private fun getCustomDataFromFCM(): String? {
        Log.i(TAG, "${ intent.extras?.getString(DemoService.IMAGE_URL_KEY) }")
        return intent.extras?.getString(DemoService.IMAGE_URL_KEY)
    }

    }






//    // Crash
//    btn.setOnClickListener() {
//        throw RuntimeException("Test Crash")
//    }
//    myFunction()
////    Log.d("onCreate", "${intent.extras?.getString("Data")}")
//}
//    @AddTrace(name="onStartTrace")
//    override fun onStart() {
//        super.onStart()
//    }
//
//    private fun myFunction() {
//        myTrace.start()
//        myTrace.incrementMetric("hit",1)
//
//
//        myTrace.stop()
//    }
//        firebaseAnalytics= Firebase.analytics
//
//        btn.setOnClickListener(){
////            throw RuntimeException("Test crash")//
//            setUpUserProperty()
//            setDefaultParameters()
//            logEvent()
//
//        }
//    }
//
//    private fun logEvent(){
//
//        val eventName2 = "share_image"
//        val bundle = Bundle().apply {
//            putString("image_name", "some_name")
//            putString("image_description", "some_text")
//        }
//        firebaseAnalytics.logEvent(eventName2, bundle)
//
//    }
//
//    private fun setUpUserProperty() {
//        firebaseAnalytics.setUserProperty("location", "INDIA")
//        firebaseAnalytics.setUserProperty("currency", "INR")
//        firebaseAnalytics.setUserProperty("is_subscribe", "false")
//    }
//
//    private fun setUserId(){
//        firebaseAnalytics.setUserId("1008")
//    }
//
//    private fun setDefaultParameters() {
//        val parameters = Bundle().apply {
//            putString(FirebaseAnalytics.Param.LOCATION, "INDIA")
//            putString(FirebaseAnalytics.Param.CURRENCY, "INR")
//        }
//
//        firebaseAnalytics.setDefaultEventParameters(null)
//    }
