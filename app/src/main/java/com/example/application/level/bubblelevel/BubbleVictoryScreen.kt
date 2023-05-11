package com.example.application.level.bubblelevel

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.application.MyActivity
import com.example.application.R
import com.example.application.SpecialLevels

class BubbleVictoryScreen: MyActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bubblelevel_victoryscreen)
    }

    fun onRestartButtonClick(view: View) {
        startActivity(Intent(this, BubbleLevel::class.java))
        finish()
    }

    fun onExitButtonClick(view: View) {
        finish()
    }
}