package com.example.architecture.mvp.presenter

import com.example.architecture.mvp.model.Board
import com.example.architecture.mvp.view.TicTacToeView

class TicTacToePresenter():Presenter {


    lateinit var view: TicTacToeView
    constructor(view:TicTacToeView):this(){
        this.view = view
    }


    var model: Board = Board()

    override fun onCreate() {
        model.restart()
        resetUI()
    }

    private fun resetUI() {
        for (i in 0..2) {
            for (j in 0..2) {
                view.setButtonText(i, j, "")
            }
        }
    }



    //to mark the box
    fun onButtonSelected(row: Int, col: Int) {
        var playerThatMoved = model.mark(row, col)

        if (playerThatMoved !=null){
            view.setButtonText(row, col, playerThatMoved.toString())

            if (model.getGameWinner()!=null)
            {
                view.showWinner(playerThatMoved.toString())
            }

        }
        else
            view.showWinner("Its a Draw")
    }

}