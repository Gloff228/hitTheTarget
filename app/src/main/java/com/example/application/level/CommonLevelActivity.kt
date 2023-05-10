package com.example.application.level

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.application.R
import com.example.application.level.figures.FigureComCircle
import com.example.application.utils.globalSettings
import kotlin.random.Random

class CommonLevelActivity: AbstractLevelActivity() {
    var clickTime: Int = -1
    var figuresNumber: Int = -1
    var figureSize: Int = -1

    var lastClickAt: Long = 0  // milliseconds

    lateinit var scoreView: TextView
    lateinit var durationView: TextView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handleInfoViews()
        getSettings()
    }

    private fun handleInfoViews() {
        scoreView = findViewById(R.id.score)
        durationView = findViewById(R.id.duration)
        if (globalSettings.backgroundColor.isDark()) {
            scoreView.setTextColor(Color.WHITE)
            durationView.setTextColor(Color.WHITE)
        }
    }

    private fun createLevel() {
        createFigures()
    }

    override fun onLevelStart() {
        lastClickAt = System.currentTimeMillis()
        updateScore()
    }

    override fun onStart() {
        super.onStart()

        createLevel()
    }

    override fun calculateNewFrame(startTime: Long) {
        super.calculateNewFrame(startTime)

        updateDuration()
    }

    private fun createFigures() {
        figures = ArrayDeque(figuresNumber)
        for (i in 1..figuresNumber) {
            val figure = FigureComCircle(figureSize)
            figure.bindLevel(this)
            val padding = (figureSize * 0.3).toInt()
            figure.setPosition(
                Random.nextInt(padding, WIDTH - padding),
                Random.nextInt(padding, HEIGHT - padding)
            )
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

    @SuppressLint("SetTextI18n")
    fun updateScore() {
        scoreView.text = "${figures.size}/${figuresNumber}"
    }

    @SuppressLint("SetTextI18n")
    fun updateDuration() {
        updateScore()
        val timeRemain = clickTime - (System.currentTimeMillis() - lastClickAt)
        if (timeRemain <= 0) {
            finishLevel()  // TODO add losing screen
            return
        }
        val newText = "${timeRemain / 1000}.${timeRemain % 1000 / 100} сек "
//        durationView.text = newText
        if (durationView.text.toString() != newText) {
            durationView.text = newText
        }
    }

    override fun loadContentView() {
        setContentView(R.layout.activity_common_level)
    }

    override fun setNewActiveFigure() {
        figures.removeLast()
        updateScore()
        lastClickAt = System.currentTimeMillis()
        if (figures.isNotEmpty()) {
            figures.last().setActive()
            needRedraw = true
        } else {
            finishLevel()  // TODO add winning screen
        }
    }
}