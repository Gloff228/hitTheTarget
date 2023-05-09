package com.example.application.level

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.application.level.figures.FigureEasyCircle

class CommonLevelActivity: AbstractLevelActivity() {
    var clickTime: Int = -1
    var figuresNumber: Int = -1
    var figureSize: Int = -1

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getSettings()
        createLevel()
    }

    private fun createLevel() {
        figures = ArrayDeque(figuresNumber)
        for (i in 1..figuresNumber) {
            val figure = FigureEasyCircle(figureSize)
            figure.bindLevel(this)
            figures.addLast(figure)
        }
        figures.last().setActive()
    }

    private fun getSettings() {
        figuresNumber = intent.getIntExtra(CommonLevelSettingsActivity.PARAM_FIGURES_NUMBER, -1)
        figureSize = intent.getIntExtra(CommonLevelSettingsActivity.PARAM_FIGURE_SIZE, -1)
        clickTime = intent.getIntExtra(CommonLevelSettingsActivity.PARAM_CLICK_TIME, -1) * 100
    }

    override fun finishLevel() {
        // TODO show finish modal (with replay level button or exit)
        finish()
    }
}