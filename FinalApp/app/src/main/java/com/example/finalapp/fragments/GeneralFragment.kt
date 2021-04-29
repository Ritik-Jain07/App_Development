package com.example.finalapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalapp.CustomAdapter
import com.example.finalapp.MyViewModel
import com.example.finalapp.R
import com.example.finalapp.model.DataModel

class GeneralFragment: Fragment() {

//    lateinit var recyclerView:RecyclerView
//    private lateinit var customAdapter:CustomAdapter
//    private var viewModelInstance:MyViewModel?=null
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_general,container,false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        viewModelInstance = ViewModelProviders.of(activity!!).get(MyViewModel::class.java)
//
//        setupUI()
//
//        viewModelInstance!!.getData().observe()
//    }
//
//    private fun setupUI(){
//        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
//        recyclerView.layoutManager = linearLayoutManager
//
//        customAdapter = CustomAdapter(this, dataList)
//        recyclerView.adapter= this.customAdapter
//    }


    val myViewModel = MyViewModel()
    val

    myViewModel.loadData().observe(this, object: Observer<List<DataModel>> {

        override fun onChanged(t: List<DataModel>?) {
            val customAdapter:CustomAdapter= CustomAdapter.addData(t!!)
            CustomAdapter.notifyDataSetChanged()
        }

    })
}