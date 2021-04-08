package com.example.Navigation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.Navigation.MainActivity
import com.example.Navigation.R
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    lateinit var firebaseAnalytics: FirebaseAnalytics
    private val MY_PREFERENCES = MainActivity.MY_PREFERENCES
    private val NAME_KEY = MainActivity.NAME_KEY
    private lateinit var navController: NavController
    private val TAG = HomeFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        logScreenEvent()
        navController = findNavController()
        checkLogin()
        setListeners()
        // Returning a result to previous destination
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>(MainActivity.DESCRIPTION_KEY)
            ?.observe(viewLifecycleOwner, Observer {
                // Do something with result
                Log.i(TAG, "Description is $it")
                logDescriptionUpdatedEvent()
                updateUIForDescription(it)
            })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Initialise Firebase analytics
        firebaseAnalytics = Firebase.analytics
    }

    private fun updateUIForDescription(description: String) {
        description_TV.text = description
    }

    private fun logDescriptionUpdatedEvent() {
        // custom event
        val eventName = "description_updated"
        val bundle = Bundle().apply {
            putString("description_changed", "yes")
        }
        firebaseAnalytics.logEvent(eventName, bundle)
    }




    private fun logScreenEvent() {
        // custom event
        val eventName = "screen_opened"
        val bundle = Bundle().apply {
            putString("screen_name", HomeFragment::class.java.simpleName)
        }
        firebaseAnalytics.logEvent(eventName, bundle)
    }

    /**
     * Check if user logged in
     * If not then redirect to Login fragment
     */
    private fun checkLogin() {
        if (isUserLoggedIn())
        // Do nothing as user is logged in
        else {
            Toast.makeText(activity, getString(R.string.name_must_enter_info), Toast.LENGTH_LONG)
                .show()
            navigateToLoginDestination()
        }
    }

    private fun isUserLoggedIn(): Boolean {
        val sharedPreferences = activity?.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE)
        val name = sharedPreferences?.getString(NAME_KEY, "")
        return name != ""
    }

    private fun setListeners() {
        openAccount_BTN.setOnClickListener {
            navigateToAccountDestination()
        }

        addDescription_BTN.setOnClickListener {
            if (!isTitleEntered()) {
                val title = title_ET.text.toString()
                navigateToInputDescriptionDialogFragmentDestination(title)
            } else {
                title_ET.error = "You must enter title"
            }
        }
    }

    private fun navigateToInputDescriptionDialogFragmentDestination(title: String) {
        // Navigate to a destination
        val action =
            HomeFragmentDirections.actionHomeFragmentToInputDescriptionDialogFragment(title) // Sending data using Safe args
        navController.navigate(action)
    }

    private fun isTitleEntered(): Boolean {
        return title_ET.text.toString().isEmpty()
    }

    private fun navigateToAccountDestination() {
        // Navigate to a destination
        val action =
            HomeFragmentDirections.actionHomeFragmentToAccountFragment()
        navController.navigate(action)
    }

    private fun navigateToLoginDestination() {
        // Navigate to a destination
        navController.navigate(R.id.action_global_loginFragment)
    }
}

