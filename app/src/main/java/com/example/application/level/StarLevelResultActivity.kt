package com.example.application.level

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.application.MyActivity
import com.example.application.R

class StarLevelResultActivity : MyActivity() {
    var figureScale: Int = 3
    var figuresNumber: Int = 20
    var figureSize: Int = 100
    var animationTime: Int = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_star_level_result)
        getSettings()
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