package com.example.application

import android.content.Intent
import android.os.Bundle
import android.view.View

class LevelMenu : MyActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_menu)
    }

    fun onClickReturnButton(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun defaultButton(view: View) {
        startActivity(Intent(this, LevelSelectionScreen::class.java))
    }

    fun specialButton(view: View) {
        startActivity(Intent(this, SpecialLevels::class.java))
    }
}