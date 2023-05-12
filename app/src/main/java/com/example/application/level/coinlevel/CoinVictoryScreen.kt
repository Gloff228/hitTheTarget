package com.example.application.level.coinlevel

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.application.MyActivity
import com.example.application.R

class CoinVictoryScreen: MyActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coinlevel_victoryscreen)
    }

    fun onRestartButtonClick(view: View) {
        startActivity(Intent(this, CoinLevel::class.java))
        finish()
    }

    fun onExitButtonClick(view: View) {
        finish()
    }
}