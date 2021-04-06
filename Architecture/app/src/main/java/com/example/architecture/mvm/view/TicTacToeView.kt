package com.example.architecture.mvm.view

interface TicTacToeView {
    fun setButtonText(row:Int, col:Int, text:String)
    fun showWinner(winningPlayer: String)

}