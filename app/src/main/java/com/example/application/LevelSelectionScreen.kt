package com.example.application

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.application.level.CommonLevelActivity
import com.example.application.level.CommonLevelSettingsActivity

class LevelSelectionScreen : MyActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_selection_screen)
    }

    fun onClickReturnButton(view: View) {
        finish()
    }

    fun onClickStartLevelButton(view: View) {
        startActivity(Intent(this, CommonLevelSettingsActivity::class.java))
    }
}