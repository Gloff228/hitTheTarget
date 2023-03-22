package com.example.application

import android.content.Intent
import android.os.Bundle
import android.view.View

class LevelSelectionScreen : MyActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_selection_screen)
        hideElements()
        setBackground(selectedBackgroundColor)
    }

    fun onClickReturnButton(view: View) {
        startActivity(Intent(this, LevelMenu::class.java))
    }

    fun selectLevel1(view: View) {
        startActivity(Intent(this, Level1::class.java))
    }
}