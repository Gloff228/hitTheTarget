package com.example.application

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.application.level.CommonLevelSettingsActivity

class LevelMenu : MyActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_menu)
    }

    fun onClickReturnButton(view: View) {
        finish()
    }

    fun commonLevelButton(view: View) {
        startActivity(Intent(this, CommonLevelSettingsActivity::class.java))
    }

    fun specialButton(view: View) {
        startActivity(Intent(this, SpecialLevels::class.java))
    }
}