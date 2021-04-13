package com.example.architecture.mvp.mvpactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.architecture.R
import com.example.architecture.mvp.presenter.TicTacToePresenter
import com.example.architecture.mvp.view.TicTacToeView
import kotlinx.android.synthetic.main.activity_mvp_main.*

class MvpMainActivity : AppCompatActivity(), TicTacToeView,View.OnClickListener {

    lateinit var winnerPlayerLabel: TextView
    lateinit var winnerPlayerViewGroup:View
    lateinit var buttonGrid: ViewGroup
    private val TAG = MvpMainActivity::class.java.simpleName
    var buttons = arrayOfNulls<Button>(9)

    val presenter = TicTacToePresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvp_main)

        winnerPlayerLabel = findViewById(R.id.winnerPlayerLabel)
        winnerPlayerViewGroup = findViewById(R.id.winnerPlayerViewGroup)
        buttonGrid = findViewById(R.id.buttonGrid)
        // var clicked = findViewById<Button>(R.id.clicked)

        //create the view
        presenter.onCreate()
        for (i in 0..buttons.size - 1) {
            var buttonnID = "btn_" + i
            var resourceId = resources.getIdentifier(buttonnID, "id", packageName)
            buttons[i] = findViewById(resourceId)
            buttons[i]!!.setOnClickListener(this)
        }

        resetGame_BTN.setOnClickListener {
            resetGame()
        }
    }

    /**
     * It will restart the game
     */
    private fun resetGame() {
        Log.i(TAG, "Game reset")
        winnerPlayerViewGroup.visibility=View.GONE
        presenter.onCreate()
    }

    fun onClickButton(view: View) {}
    override fun setButtonText(row: Int, col: Int, text: String) {
        var btn = buttonGrid.findViewWithTag<Button>("" + row + col)
        if (btn != null){
            btn.setText(text)
        }
    }

    override fun showWinner(winningPlayer: String) {
        winnerPlayerLabel.setText(winningPlayer)
        winnerPlayerViewGroup.visibility=View.VISIBLE
    }

    override fun onClick(v: View?) {
        var buttonId = v!! as Button
        val tag = buttonId.getTag().toString()


        //spliting tag into row and col
        var row = tag.substring(0,1).toInt()
        var col = tag.substring(1,2).toInt()

        //send row and col to presenter
        presenter.onButtonSelected(row,col)
    }

}