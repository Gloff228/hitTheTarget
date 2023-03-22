package com.example.application

import android.content.Intent
import android.os.Bundle
import android.view.View

class BackgroundScreen : MyActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background_screen)
        hideElements()
        setBackground(selectedBackgroundColor)
    }

    fun selectWhite(view: View) {
        selectedBackgroundColor = BackgroundColor.WHITE
        setBackground(selectedBackgroundColor)
    }

    fun selectGray(view: View) {
        selectedBackgroundColor = BackgroundColor.GRAY
        setBackground(selectedBackgroundColor)
    }

    fun selectPink(view: View) {
        selectedBackgroundColor = BackgroundColor.PINK
        setBackground(selectedBackgroundColor)
    }

    fun selectRed(view: View) {
        selectedBackgroundColor = BackgroundColor.RED
        setBackground(selectedBackgroundColor)
    }

    fun selectBlue(view: View) {
        selectedBackgroundColor = BackgroundColor.BLUE
        setBackground(selectedBackgroundColor)
    }

    fun selectPurple(view: View) {
        selectedBackgroundColor = BackgroundColor.PURPLE
        setBackground(selectedBackgroundColor)
    }

    fun onClickButtonSettingsBack(view: View) {
        startActivity(Intent(this, SettingsScreen::class.java))
    }

}