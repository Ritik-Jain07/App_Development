package com.example.fragmentapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import kotlinx.android.synthetic.main.fragment_deatils_view.*
import java.util.Observer

/**
 * A simple [Fragment] subclass.
 * Use the [DeatilsView.newInstance] factory method to
 * create an instance of this fragment.
 */
class DeatilsView : Fragment() {
    private val viewModel: ViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedItem.observe(viewLifecycleOwner, { item ->
            detailview.text = item
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deatils_view, container, false)
    }




}