package com.example.application.level

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.application.MyActivity
import com.example.application.R
import com.example.application.level.utils.LevelSettingsItem
import com.example.application.level.utils.LevelSettingsItemViews

open class AbsLevelSettingsActivity: MyActivity() {

    private val SETTINGS_N = 5  // Number of settings block in xml layout

    // this is settings list. You need to specify settings info here
    // Если хотите больше 5ти настроек, то у вас проблемы, потому что в разметке xml их только 5))
    // you must define this
    open val SETTINGS = listOf<LevelSettingsItem>()

    val resultSettings = mutableMapOf<String, Int>()

    private lateinit var settingViews: MutableList<LevelSettingsItemViews>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadContentView()
        initSettings()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initSettings() {
        initResultSettings()
        findSettingsViews()
        updateSettingViews()
        deleteUnusedSettingViews()
    }

    private fun initResultSettings() {
        for (setting in SETTINGS) {
            resultSettings[setting.name] = setting.startValue
        }
    }

    @SuppressLint("DiscouragedApi")
    fun findSettingsViews() {
        settingViews = mutableListOf()
        for (i in 1..SETTINGS.size) {
            val nameViewId = resources.getIdentifier("settingName${i}", "id", packageName)
            val nameView = findViewById<TextView>(nameViewId)

            val valueViewId = resources.getIdentifier("settingValue${i}", "id", packageName)
            val valueView = findViewById<TextView>(valueViewId)

            val seekBarId = resources.getIdentifier("seekBar${i}", "id", packageName)
            val seekBar = findViewById<SeekBar>(seekBarId)

            val settingsItemViews = LevelSettingsItemViews(nameView, valueView, seekBar)
            settingViews.add(settingsItemViews)
        }
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateSettingViews() {
        for (i in settingViews.indices) {
            val settingsItem = SETTINGS[i]
            val views = settingViews[i]

            views.nameView.text = settingsItem.name
            if (settingsItem.name == "Длительность")
                views.valueView.text = "${settingsItem.startValue / 10}.${settingsItem.startValue % 10}"
            else
                views.valueView.text = settingsItem.startValue.toString()
            views.seekBar.min = settingsItem.minValue / settingsItem.step
            views.seekBar.max = settingsItem.maxValue / settingsItem.step
            views.seekBar.progress = settingsItem.startValue / settingsItem.step

            views.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                @SuppressLint("SetTextI18n")
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    /** Костыль для длительности
                     *  Задавайте её значение 0.1 = 1. Например 1.3 секунды записывайте как 13,
                     *  а 2 секунды как 20.
                     *  Соответствено, min значение для 0.5 секунд = 5.
                     *  Такая штука будет работать только при name="Длительность"
                     *
                     * */
                    val actualProgress = progress * settingsItem.step
                    resultSettings[settingsItem.name] = actualProgress
                    if (settingsItem.name == "Длительность")
                        views.valueView.text = "${actualProgress / 10}.${actualProgress % 10}"
                    else
                        views.valueView.text = actualProgress.toString()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })
        }
    }

    @SuppressLint("DiscouragedApi")
    private fun deleteUnusedSettingViews() {
        val gridLayout = findViewById<GridLayout>(R.id.gridLayout)

        for (i in settingViews.size + 1..SETTINGS_N) {
            val block1Id = resources.getIdentifier("setting${i}_1", "id", packageName)
            val block1 = findViewById<View>(block1Id)

            val block2Id = resources.getIdentifier("setting${i}_2", "id", packageName)
            val block2 = findViewById<View>(block2Id)

            gridLayout.removeView(block1)
            gridLayout.removeView(block2)
        }
    }

    fun onClickReturnButton(view: View) {
        finish()
    }

    fun onClickStartLevelButton(view: View) {
        // Start level
        val levelIntent = getLevelIntent()
        for ((name, value) in resultSettings) {
            levelIntent.putExtra(name, value)
        }
        // TODO save settings to storage
        startActivity(levelIntent)
    }

    /** Ниже функции, которые можно переопределять */

    open fun loadContentView() {
        // You can define your custom xml layout for settings here
        setContentView(R.layout.activity_level_settings)

        // redefine this
    }

    open fun getLevelIntent(): Intent {
        // create intent here to start your level
        // for example: Intent(this, VegetablesLevel::class.java)

        // you must define this
        throw NotImplementedError()
    }

    // also define SETTINGS
}