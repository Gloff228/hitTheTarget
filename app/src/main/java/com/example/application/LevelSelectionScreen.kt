package com.example.application

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.application.level.CommonLevelActivity

class LevelSelectionScreen : MyActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_selection_screen)
    }

    fun onClickReturnButton(view: View) {
        finish()
    }

    fun startLevel() {
        val CLICK_TIME = 30_000 // 30 seconds // TODO get from settings
        val FIGURES_NUMBER = 5  // TODO get from settings
        val FIGURE_SIZE = 300  // TODO get from settings

        val intent = Intent(this, CommonLevelActivity::class.java)
        intent.putExtra(CommonLevelActivity.PARAM_CLICK_TIME, CLICK_TIME)
        intent.putExtra(CommonLevelActivity.PARAM_FIGURES_NUMBER, FIGURES_NUMBER)
        intent.putExtra(CommonLevelActivity.PARAM_FIGURE_SIZE, FIGURE_SIZE)
        startActivity(intent)
    }

    fun onClickStartLevelButton(view: View) {
        startLevel()
    }
}