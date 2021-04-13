package com.example.architecture.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.architecture.R
import com.example.architecture.databinding.ActivityMvvmMainBinding
import com.example.architecture.mvp.model.Player

class MvvmMainActivity : AppCompatActivity() {

    var buttons = arrayOfNulls<Button>(9)
    var activityGameBinding: ActivityMvvmMainBinding? = null

    lateinit var gameViewModel: MvvmViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        activityGameBinding= DataBindingUtil.setContentView(this, R.layout.activity_mvvm_main)
        gameViewModel = ViewModelProviders.of(this).get(MvvmViewModel::class.java)
        activityGameBinding?.gameViewModel = gameViewModel



        gameViewModel.getWinner()?.observe(this, Observer {
            winner:Player?->onGameWinnerChanged(winner!!)
        })

    }

    fun onGameWinnerChanged(winner: Player?) {
        if (winner == null)
            activityGameBinding?.winnerPlayerLabelmvvm?.text = "it's a tie"
        else
            activityGameBinding?.winnerPlayerLabelmvvm?.text = winner.toString()

    }
}