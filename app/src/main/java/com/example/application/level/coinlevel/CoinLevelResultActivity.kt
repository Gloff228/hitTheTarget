package com.example.application.level.coinlevel

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.application.MyActivity
import com.example.application.R


class CoinLevelResultActivity: MyActivity() {
    var figuresNumber = -1
    var figureSize = -1
    var figureSpeed = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_level_win)

        applySettings()
        getSettings()
    }

    private fun getSettings() {
        figuresNumber = intent.getIntExtra(CoinSettings.PARAM_FIGURES_NUMBER, -1)
        figureSize = intent.getIntExtra(CoinSettings.PARAM_FIGURE_SIZE, -1)
        figureSpeed = intent.getIntExtra(CoinSettings.PARAM_SPEED, -1)
    }

    @SuppressLint("SetTextI18n")
    private fun applySettings() {
        val resultTextView = findViewById<TextView>(R.id.resultText)
        val descriptionView = findViewById<TextView>(R.id.description)

        resultTextView.text = "Молодец!"
        descriptionView.text = "Ты собрал все монетки \n Давай попробуем еще раз?"
    }

    fun onClickButtonRetry(view: View) {
        val intent = Intent(this, CoinLevel::class.java)
        intent.putExtra(CoinSettings.PARAM_FIGURES_NUMBER, figuresNumber)
        intent.putExtra(CoinSettings.PARAM_FIGURE_SIZE, figureSize)
        intent.putExtra(CoinSettings.PARAM_SPEED, figureSpeed)
        startActivity(intent)
        finish()
    }

    fun onClickButtonBack(view: View) {
        finish()
    }
}