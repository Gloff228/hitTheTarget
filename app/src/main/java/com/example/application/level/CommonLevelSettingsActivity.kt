package com.example.application.level

import android.content.Intent
import com.example.application.level.utils.LevelSettingsItem

class CommonLevelSettingsActivity: AbsLevelSettingsActivity() {
    companion object {
        const val PARAM_FIGURES_NUMBER = "Количество"
        const val PARAM_FIGURE_SIZE = "Размер"
        const val PARAM_CLICK_TIME = "Длительность"
    }

    override val SETTINGS = listOf(
        LevelSettingsItem(PARAM_FIGURES_NUMBER, 3,50, 1, 10),
        LevelSettingsItem(PARAM_FIGURE_SIZE, 100,800, 5, 400),
        LevelSettingsItem(PARAM_CLICK_TIME, 100, 30_000, 50, 5000),
    )

    override fun getLevelIntent(): Intent {
        return Intent(this, CommonLevelActivity::class.java)
    }
}