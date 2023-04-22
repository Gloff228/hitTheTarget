package com.example.application.level

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.application.LevelMenu
import com.example.application.LevelSelectionScreen
import com.example.application.level.figures.FigureEasyCircle
import com.example.application.level.utils.LevelSettings
import com.example.application.utils.Difficulty
import kotlin.math.min

class CommonLevelActivity: AbstractLevelActivity() {

    companion object {
        // pass via intent extras
        const val PARAM_CLICK_TIME = "PARAM_CLICK_TIME"
        const val PARAM_FIGURES_NUMBER = "PARAM_FIGURES_NUMBER"
        const val PARAM_FIGURE_SIZE = "PARAM_FIGURE_SIZE"
    }

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
        clickTime = intent.getIntExtra(PARAM_CLICK_TIME, -1)
        figuresNumber = intent.getIntExtra(PARAM_FIGURES_NUMBER, -1)
        figureSize = intent.getIntExtra(PARAM_FIGURE_SIZE, -1)
    }

    override fun finishLevel() {
        // TODO show finish modal (with replay level button or exit)
        finish()
    }
}