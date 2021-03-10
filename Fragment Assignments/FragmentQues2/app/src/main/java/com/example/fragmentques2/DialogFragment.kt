package com.example.fragmentques2

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.DialogFragment

class DialogFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View=inflater.inflate(R.layout.activity_dialog,container,false)
        return view
    }
    override fun onCreateDialog(savedInstanceState: Bundle?):Dialog =
        AlertDialog.Builder(requireContext()).setMessage("Dialog Fragment").setPositiveButton("OK") {
        _,_ ->
    }.create()

    companion object{
        const val tag = "Dialog Fragment"
    }
}


