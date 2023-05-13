package com.example.application.level

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.application.MyActivity
import com.example.application.R

class CommonLevelResultActivity: MyActivity() {
    val winColor = 0x00751B
    val looseColor = 0x751400

    var figuresNumber = -1
    var figureSize = -1
    var clickTime = -1

    var isWin = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_level_win)

        getSettings()
        applySettings()
    }

    private fun applySettings() {
        val resultTextView = findViewById<TextView>(R.id.resultText)
        val descriptionView = findViewById<TextView>(R.id.description)

        if (isWin) {
            resultTextView.text = "Победа!"
            resultTextView.setTextColor(Color.GREEN)
            descriptionView.text = "Молодец, так держать,\nТы отлично справился!"
        } else {
            resultTextView.text = "Поражение"
            resultTextView.setTextColor(Color.RED)
            descriptionView.text = "Попробуй ещё раз"
        }
    }

    private fun getSettings() {
        figuresNumber = intent.getIntExtra(CommonLevelSettingsActivity.PARAM_FIGURES_NUMBER, -1)
        figureSize = intent.getIntExtra(CommonLevelSettingsActivity.PARAM_FIGURE_SIZE, -1)
        clickTime = intent.getIntExtra(CommonLevelSettingsActivity.PARAM_CLICK_TIME, -1) * 100

        isWin = intent.getBooleanExtra(CommonLevelActivity.PARAM_IS_WIN, false)
    }

    fun onClickButtonBack(view: View) {
        finish()
    }

    fun onClickButtonRetry(view: View) {
        val intent = Intent(this, CommonLevelActivity::class.java)
        intent.putExtra(CommonLevelSettingsActivity.PARAM_FIGURES_NUMBER, figuresNumber)
        intent.putExtra(CommonLevelSettingsActivity.PARAM_FIGURE_SIZE, figureSize)
        intent.putExtra(CommonLevelSettingsActivity.PARAM_CLICK_TIME, clickTime / 10000)
        startActivity(intent)
        finish()
    }
}