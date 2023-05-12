package com.example.application.level

import android.content.Intent
import com.example.application.level.utils.LevelSettingsItem

class StarLevelSettings: AbsLevelSettingsActivity() {
    companion object {
        const val PARAM_FIGURES_NUMBER = "Количество"
        const val PARAM_FIGURE_SIZE = "Размер"
        const val PARAM_ANIMATION_TIME = "Длительность анимации"
        const val PARAM_FIGURE_SCALE = "Увеличение в"
    }

    override val SETTINGS = listOf(
        LevelSettingsItem(PARAM_FIGURES_NUMBER, 10,30, 1, 20),
        LevelSettingsItem(PARAM_FIGURE_SIZE, 50,150, 10, 100),
        LevelSettingsItem(PARAM_ANIMATION_TIME, 0, 2000, 100, 1000),
        LevelSettingsItem(PARAM_FIGURE_SCALE, 2, 4, 1, 3),
    )

    override fun getLevelIntent(): Intent {
        return Intent(this, StarLevel::class.java)
    }
}
