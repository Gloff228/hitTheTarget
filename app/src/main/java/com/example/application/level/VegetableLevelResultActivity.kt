package com.example.application.level

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.application.MyActivity
import com.example.application.R

class VegetableLevelResultActivity: MyActivity() {
    var figuresNumber: Int = -1
    var figureSize: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_level_win)

        applySettings()
        getSettings()
    }

    private fun getSettings() {
        figuresNumber = intent.getIntExtra(VegetableLevelSettingsActivity.PARAM_FIGURES_NUMBER, -1)
        figureSize = intent.getIntExtra(VegetableLevelSettingsActivity.PARAM_FIGURE_SIZE, -1)
    }

    @SuppressLint("SetTextI18n")
    private fun applySettings() {
        val resultTextView = findViewById<TextView>(R.id.resultText)
        val descriptionView = findViewById<TextView>(R.id.description)

        resultTextView.text = "Молодец!"
        descriptionView.text = "Ты собрал все овощи \n Хочешь попробовать еще раз?"
    }

    fun onClickButtonRetry(view: View) {
        val intent = Intent(this, VegetablesLevel::class.java)
        intent.putExtra(VegetableLevelSettingsActivity.PARAM_FIGURES_NUMBER, figuresNumber)
        intent.putExtra(VegetableLevelSettingsActivity.PARAM_FIGURE_SIZE, figureSize)
        startActivity(intent)
        finish()
    }

    fun onClickButtonBack(view: View) {
        finish()
    }
}