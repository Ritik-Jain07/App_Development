package com.example.jetpack.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.jetpack.PersonDataClass
import com.example.jetpack.R
import com.example.jetpack.viewmodels.PersonViewModel
import kotlinx.android.synthetic.main.fragment_add_data.*

class AddDataFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_data, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachListeners()
    }

    private fun attachListeners() {
        addData_BTN.setOnClickListener {
            addDataInRoomDB()


        }

        /**
         * Go back to previous fragment
         * from the stack
         */
        cancelAddingDetails_BTN.setOnClickListener {
            fragmentManager?.popBackStack()

        }
    }

    private fun addDataInRoomDB() {

        if(personName_ET.text.toString().isBlank()){
            personName_ET.error = "Please enter name"
        }
        else if(personContact_ET.text.toString().isBlank()){
            personContact_ET.error = "Please enter Mobile No."
        }
        else if(personAddress_ET.text.toString().isBlank()){
            personAddress_ET.error = "Please enter address"
        } else{
            Toast.makeText(context, "Data added successfully", Toast.LENGTH_LONG).show()
            fragmentManager?.popBackStack()
        val application = activity!!.application
        val personViewModel = ViewModelProvider(this).get(PersonViewModel(application)::class.java)
        personViewModel.addPersonDetails(PersonDataClass(
            personId = null,
            personName = personName_ET.text.toString(),
            personContact = personContact_ET.text.toString(),
            personAddress = personAddress_ET.text.toString()
        ))
    }}
}