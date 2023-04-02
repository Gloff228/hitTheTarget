package com.example.application

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.application.level.AbstractLevelActivity
import com.example.application.level.CommonLevelActivity

class LevelSelectionScreen : MyActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_selection_screen)
    }

    fun onClickReturnButton(view: View) {
        finish()
    }

    fun startLevel(levelNumber: Int) {
        val intent = Intent(this, CommonLevelActivity::class.java)
        intent.putExtra(CommonLevelActivity.PARAM_LEVEL_NUMBER, levelNumber)
        startActivity(intent)
    }

    fun onClickLevelButton(view: View) {
        view as TextView
        val levelNumber = view.text.toString().toInt()
        println(levelNumber)
        startLevel(levelNumber)
    }
}