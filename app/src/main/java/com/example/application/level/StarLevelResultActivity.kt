package com.example.application.level

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.application.MyActivity
import com.example.application.R

class StarLevelResultActivity : MyActivity() {
    var figureScale: Int = 3
    var figuresNumber: Int = 20
    var figureSize: Int = 100
    var animationTime: Int = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_level_win)

        applySettings()
        getSettings()
    }

    @SuppressLint("SetTextI18n")
    private fun applySettings() {
        val resultTextView = findViewById<TextView>(R.id.resultText)
        val descriptionView = findViewById<TextView>(R.id.description)

        resultTextView.text = "Молодец!"
        descriptionView.text = "Ты поймал все звёзды \n Можешь попробовать еще раз!"
    }

    private fun getSettings() {
        figuresNumber = intent.getIntExtra(StarLevelSettings.PARAM_FIGURES_NUMBER, 20)
        figureSize = intent.getIntExtra(StarLevelSettings.PARAM_FIGURE_SIZE, 100)
        figureScale = intent.getIntExtra(StarLevelSettings.PARAM_FIGURE_SCALE, 3)
        animationTime = intent.getIntExtra(StarLevelSettings.PARAM_ANIMATION_TIME, 1000)
    }

    fun onClickButtonRetry(view: View) {
        val intent = Intent(this, StarLevel::class.java)
        intent.putExtra(StarLevelSettings.PARAM_FIGURES_NUMBER, figuresNumber)
        intent.putExtra(StarLevelSettings.PARAM_FIGURE_SIZE, figureSize)
        intent.putExtra(StarLevelSettings.PARAM_FIGURE_SCALE, figureScale)
        intent.putExtra(StarLevelSettings.PARAM_ANIMATION_TIME, animationTime)
        startActivity(intent)
        finish()
    }

    fun onClickButtonBack(view: View) {
        finish()
    }
}