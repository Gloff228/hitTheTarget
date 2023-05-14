package com.example.application.level

import android.content.Intent
import android.widget.CheckBox
import android.widget.CompoundButton
import com.example.application.R
import com.example.application.level.utils.LevelSettingsItem


class CommonLevelSettingsActivity: AbsLevelSettingsActivity() {
    companion object {
        const val PARAM_FIGURES_NUMBER = "Количество"
        const val PARAM_FIGURE_SIZE = "Размер"
        const val PARAM_CLICK_TIME = "Длительность"
        const val PARAM_IS_ALL_FIGURES = "PARAM_IS_ALL_FIGURES"
    }

    override val SETTINGS = listOf(
        LevelSettingsItem(PARAM_FIGURES_NUMBER, 3,50, 1, 10),
        LevelSettingsItem(PARAM_FIGURE_SIZE, 100,800, 10, 400),
        LevelSettingsItem(PARAM_CLICK_TIME, 5, 300, 1, 50),
    )

    override fun getLevelIntent(): Intent {
        return Intent(this, CommonLevelActivity::class.java)
    }

    override fun loadContentView() {
        setContentView(R.layout.activity_common_level_settings)
    }

    override fun initResultSettings() {
        val loadedSettingsMap = getLoadedSettingsMap()
        for (setting in SETTINGS) {
            if (loadedSettingsMap.containsKey(setting.name)) {
                resultSettings[setting.name] = (loadedSettingsMap[setting.name] as Double).toInt()
            } else {
                resultSettings[setting.name] = setting.startValue
            }
        }
        if (loadedSettingsMap.containsKey(PARAM_IS_ALL_FIGURES)) {
            resultSettings[PARAM_IS_ALL_FIGURES] = (loadedSettingsMap[PARAM_IS_ALL_FIGURES] as Double).toInt()
        } else {
            resultSettings[PARAM_IS_ALL_FIGURES] = 0
        }
    }

    override fun afterInitSettings() {
        super.afterInitSettings()

        val allFiguresCheckbox = findViewById<CheckBox>(R.id.allFiguresCheckbox)
        allFiguresCheckbox.isChecked = resultSettings[PARAM_IS_ALL_FIGURES] != 0
        allFiguresCheckbox.setOnCheckedChangeListener { compoundButton, _ ->
            if (compoundButton.isChecked)
                resultSettings[PARAM_IS_ALL_FIGURES] = 1
            else
                resultSettings[PARAM_IS_ALL_FIGURES] = 0
        }
    }
}