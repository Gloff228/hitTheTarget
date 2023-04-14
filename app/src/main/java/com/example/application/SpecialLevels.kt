package com.example.application

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.application.level.BubbleLevel
import com.example.application.level.StarLevel

class SpecialLevels : MyActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_special_levels)
    }

    fun onClickReturnButton(view: View) {
        startActivity(Intent(this, LevelMenu::class.java))
    }

    fun onClickSpecialLevelButton(view: View) {
        startActivity(Intent(this, BubbleLevel::class.java))
    }

    fun onStarButtonClick(view: View) {
        startActivity(Intent(this, StarLevel::class.java))
    }
}