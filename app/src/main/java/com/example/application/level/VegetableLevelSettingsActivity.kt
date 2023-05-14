package com.example.application.level

import android.content.Intent
import com.example.application.level.utils.LevelSettingsItem

class VegetableLevelSettingsActivity: AbsLevelSettingsActivity() {
    companion object {
        const val PARAM_FIGURES_NUMBER = "Количество"
        const val PARAM_FIGURE_SIZE = "Размер"
    }

    override val SETTINGS = listOf(
        LevelSettingsItem(PARAM_FIGURES_NUMBER, 3,30, 1, 10),
        LevelSettingsItem(PARAM_FIGURE_SIZE, 70,500, 10, 200),
    )

    override fun getLevelIntent(): Intent {
        return Intent(this, VegetablesLevel::class.java)
    }
}