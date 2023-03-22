package com.example.application

import android.content.Intent
import android.os.Bundle
import android.view.View

class SpecialLevels : MyActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_special_levels)
        hideElements()
        setBackground(selectedBackgroundColor)
    }

    fun onClickReturnButton(view: View) {
        startActivity(Intent(this, LevelMenu::class.java))
    }
}