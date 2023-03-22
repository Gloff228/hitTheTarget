package com.example.application

import android.content.Intent
import android.os.Bundle
import android.view.*

class MainActivity : MyActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hideElements()
        setBackground(selectedBackgroundColor)
    }

    fun onClickButtonStart(view: View) {
        startActivity(Intent(this, LevelMenu::class.java))
    }

    fun onClickButtonSettings(view: View) {
        startActivity(Intent(this, SettingsScreen::class.java))
    }
}