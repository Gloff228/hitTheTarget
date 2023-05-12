package com.example.application.level.bubblelevel

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.application.MyActivity
import com.example.application.R

class BubbleVictoryScreen: MyActivity() {

    var figuresNumber = -1
    var figureSize = -1
    var figureSpeed = -1
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bubblelevel_victoryscreen)

        getSettings()
    }

    private fun getSettings() {
        figuresNumber = intent.getIntExtra(BubbleSettings.PARAM_FIGURES_NUMBER, -1)
        figureSize = intent.getIntExtra(BubbleSettings.PARAM_FIGURE_SIZE, -1)
        figureSpeed = intent.getIntExtra(BubbleSettings.PARAM_SPEED, -1)
    }
    fun onRestartButtonClick(view: View) {
        val intent = Intent(this, BubbleLevel::class.java)
        intent.putExtra(BubbleSettings.PARAM_FIGURES_NUMBER, figuresNumber)
        intent.putExtra(BubbleSettings.PARAM_FIGURE_SIZE, figureSize)
        intent.putExtra(BubbleSettings.PARAM_SPEED, figureSpeed)
        startActivity(intent)
        finish()
    }

    fun onExitButtonClick(view: View) {
        finish()
    }
}