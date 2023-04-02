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
        const val PARAM_LEVEL_NUMBER = "PARAM_LEVEL_NUMBER"

        // time in ms for which the wave must be passed. After this time penalty will start increasing
        const val EASY_CLICK_TIME = 5000
        const val NORMAL_CLICK_TIME = 3000
        const val HARD_CLICK_TIME = 2000

        const val INITIAL_FIGURES_NUMBER = 3
        const val EASY_FIGURES_MULTIPLIER = 0.5
        const val HARD_FIGURES_MULTIPLIER = 1.5

        const val DECREASING_TIME_PER_LEVEL = 50
        const val INCREASING_FIGURES_PER_LEVEL = 0.5  // set to 0.1

        const val ADDITIONAL_DIFFICULTY_PER_FIGURE = 100
        const val ADDITIONAL_DIFFICULTY_PER_TIME = 0.1

        const val MINIMUM_TIME = 1000
        const val MAXIMUM_FIGURES = 10
    }

    lateinit var levelSettings: LevelSettings

    val wavesNumber = 3  //  How much times figures will be shown
    var figuresNumber = 0  // Figures per wave to show
    var clickTimeMs = NORMAL_CLICK_TIME

    /** additionalDifficulty - важный и сложный для понимания параметр, поэтому расскажу
     *  подробно и по русски)
     *
     *  Вообщем, есть такие переходные моменты. Допустим, на 102ом уровне
     *  количество фигур было рассчитано как 15.95. figuresNumber округлится
     *  в нижнюю сторону, то есть figuresNumber = 15. Тогда на следующем уровне будет скачок
     *  сложности сразу в 1 фигуру, по сравнении с предыдущим. Чтобы сгладить такой скачок,
     *  было решено ввести параметр additionalDifficulty, который учтёт эти 0.95 фигур и
     *  возможно ещё что то.
     * */
    var additionalDifficulty = 0  // Additional parameter to increase difficulty smoothly

    var score = 0.0  // Double in  [0, 1]. 0 is min score. 1 is max score.
    var wave = 1

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val levelNumber = intent.getIntExtra(PARAM_LEVEL_NUMBER, 1)

        // to calculate figuresNumber
        var n = INITIAL_FIGURES_NUMBER + (levelNumber) * INCREASING_FIGURES_PER_LEVEL

        if (settings.difficulty == Difficulty.EASY) {
            n *= EASY_FIGURES_MULTIPLIER
            clickTimeMs = EASY_CLICK_TIME
        } else if (settings.difficulty == Difficulty.HARD) {
            n *= HARD_FIGURES_MULTIPLIER
            clickTimeMs = HARD_CLICK_TIME
        }

        figuresNumber = min(n.toInt(), MAXIMUM_FIGURES)
        additionalDifficulty += ((n - figuresNumber) * ADDITIONAL_DIFFICULTY_PER_FIGURE).toInt()

        clickTimeMs -= DECREASING_TIME_PER_LEVEL * (levelNumber)
        if (clickTimeMs < MINIMUM_TIME) {
            additionalDifficulty +=
                ((MINIMUM_TIME - clickTimeMs) * ADDITIONAL_DIFFICULTY_PER_TIME).toInt()
            clickTimeMs = MINIMUM_TIME
        }

        levelSettings = LevelSettings(
            number = levelNumber,
            figuresLeft = figuresNumber  // TODO
        )

        createNewWave()
    }

    private fun createNewWave() {
        figures = MutableList(figuresNumber) { FigureEasyCircle(200) }
    }

    override fun onHandlingNewFrame() {
        if (figures.all { figure -> !figure.isExists() }) {
            wave += 1
            if (wave <= wavesNumber) {
                createNewWave()
            } else {
                // finish level
                finish()
            }
        }
    }
}