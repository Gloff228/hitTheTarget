package com.example.application

import android.content.Intent
import android.os.Bundle
import android.view.View

class SettingsScreen : MyActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_screen)
    }

    fun onClickButtonBackground(view: View) {
        startActivity(Intent(this, BackgroundScreen::class.java))
    }

    fun onClickReturnButton(view: View) {
        finish()
    }

    fun onClickButtonColor(view: View) {
        startActivity(Intent(this, TargetColorScreen::class.java))
    }
    fun onClickButtonOutline(view: View) {}
}