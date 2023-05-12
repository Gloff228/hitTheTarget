package com.example.application.level.bubblelevel

import android.content.Intent
import com.example.application.level.AbsLevelSettingsActivity
import com.example.application.level.utils.LevelSettingsItem

class BubbleSettings: AbsLevelSettingsActivity() {

    companion object {
        const val PARAM_FIGURES_NUMBER = "Количество"
        const val PARAM_FIGURE_SIZE = "Размер"
        const val PARAM_SPEED = "Скорость"
    }

    override val SETTINGS = listOf(
        LevelSettingsItem(PARAM_FIGURES_NUMBER, 3,100, 1, 10),
        LevelSettingsItem(PARAM_FIGURE_SIZE, 70,500, 1, 200),
        LevelSettingsItem(PARAM_SPEED, 1, 30, 1, 10),
    )

    override fun getLevelIntent(): Intent {
        return Intent(this, BubbleLevel::class.java)
    }
}