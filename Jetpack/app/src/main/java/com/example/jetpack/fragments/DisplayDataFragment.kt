package com.example.jetpack.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack.R
import com.example.jetpack.adapters.PersonDetailAdapter
import com.example.jetpack.PersonDataClass
import com.example.jetpack.viewmodels.PersonViewModel
import kotlinx.android.synthetic.main.fragment_display_data.*

class DisplayDataFragment : Fragment() {

    private lateinit var customAdapter: PersonDetailAdapter
    private val TAG = "DisplayDataFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_data, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addPersonDetails_FAB.setOnClickListener {
            openAddPersonDetailsFragment()
        }

        initialiseRecyclerView()
    }

    private fun initialiseRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        personDetails_RV.layoutManager = linearLayoutManager

        setupListFromRoom()
    }

    private fun setupListFromRoom() {
        val application = activity!!.application

        // With LiveData
        val personViewModel = ViewModelProvider(this).get(PersonViewModel(application)::class.java)
        personViewModel.getAllPersonsDetails().observe(viewLifecycleOwner, Observer {
            Log.i(TAG, it.toString())
            customAdapter = PersonDetailAdapter( it as ArrayList<PersonDataClass>)
            personDetails_RV.adapter = customAdapter
        })

        // Without LiveData
       /* val roomDatabaseBuilder = context?.let { DatabaseBuilder.getInstance(it) }
        var personsList: List<PersonDataClass>
        Executors.newSingleThreadExecutor().execute {
            // get data from Database
            personsList = roomDatabaseBuilder!!.personDao().getAllPersonsDetails()

            personDetails_RV.apply {
                activity!!.runOnUiThread {
                    customAdapter = PersonDetailAdapter(context, personsList as ArrayList<PersonDataClass>)
                    personDetails_RV.adapter = customAdapter
                    customAdapter.notifyDataSetChanged()
                }
            }
        }*/
    }

    /**
     * Replace old fragment with another fragment
     * to add the new person details
     */
    private fun openAddPersonDetailsFragment() {
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragmentContainer_FL, AddDataFragment())
        // Add fragment to backstack
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
    }
}