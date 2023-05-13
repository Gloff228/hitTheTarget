package com.example.application.level

import android.annotation.SuppressLint
import android.content.Intent
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

    companion object {
        const val PARAM_IS_WIN = "PARAM_IS_WIN"
    }

    init {
        FPS = 20
    }

    // level settings
    var clickTime: Int = -1
    var figuresNumber: Int = -1
    var figureSize: Int = -1
    var isAllFigures: Int = -1

    var lastClickAt: Long = 0  // milliseconds

    lateinit var scoreView: TextView
    lateinit var durationView: TextView

    var isWin = false

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handleInfoViews()
        getSettings()
    }

    private fun handleInfoViews() {
        scoreView = findViewById(R.id.score)
//        scoreView.text = "88/88"
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
        val showInactive = if (isAllFigures == 0) false else true
        figures = ArrayDeque(figuresNumber)
        for (i in 1..figuresNumber) {
            val figure = FigureComCircle(figureSize)
            figure.bindLevel(this)
            figure.setShowInactive(showInactive)
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
        isAllFigures = intent.getIntExtra(CommonLevelSettingsActivity.PARAM_IS_ALL_FIGURES, -1)
    }

    override fun finishLevel() {
        if (!threadQuit) {
            threadQuit = true
            finishActivity(0)
            finish()
            openResultScreen()
        }
    }

    private fun openResultScreen() {
        val intent = Intent(this, CommonLevelResultActivity::class.java)
        intent.putExtra(CommonLevelSettingsActivity.PARAM_FIGURES_NUMBER, figuresNumber)
        intent.putExtra(CommonLevelSettingsActivity.PARAM_FIGURE_SIZE, figureSize)
        intent.putExtra(CommonLevelSettingsActivity.PARAM_CLICK_TIME, clickTime)
        intent.putExtra(PARAM_IS_WIN, isWin)
        startActivity(intent)
    }

    @SuppressLint("SetTextI18n")
    fun updateScore() {
        val newText = "${figuresNumber - figures.size}/${figuresNumber}"
        if (scoreView.text.toString() != newText) {
            runOnUiThread {
                scoreView.setText(newText, TextView.BufferType.SPANNABLE)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun updateDuration() {
//        updateScore()  // Я не знаю почему, но без обновленя счёта обновление времени не происходит
        val timeRemain = clickTime - (System.currentTimeMillis() - lastClickAt)
        if (timeRemain <= 0) {
            isWin = false
            finishLevel()
            return
        }
        val newText = "${timeRemain / 1000}.${timeRemain % 1000 / 100} сек "
        if (durationView.text.toString() != newText) {
            runOnUiThread {
                durationView.setText(newText, TextView.BufferType.SPANNABLE)
            }
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
            isWin = true
            finishLevel()
        }
    }
}