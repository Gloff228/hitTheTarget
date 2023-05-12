package com.example.application

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.example.application.utils.BackgroundColor

class BackgroundScreen : MyActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background_screen)
    }

    private fun handleColorChange() {
        if (settings.backgroundColor.isDark()) {
            handleDarkMode()
        } else {
            val root = findViewById<ViewGroup>(android.R.id.content).rootView as ViewGroup
            setTextColorForAll(root, Color.BLACK)  // change textColor to white
        }
    }

    fun selectWhite(view: View) {
        setBackgroundColor(BackgroundColor.WHITE)
        handleColorChange()
    }

    fun selectBlack(view: View) {
        setBackgroundColor(BackgroundColor.BLACK)
        handleColorChange()
    }

    fun selectPink(view: View) {
        setBackgroundColor(BackgroundColor.PINK)
        handleColorChange()
    }

    fun selectRed(view: View) {
        setBackgroundColor(BackgroundColor.RED)
        handleColorChange()
    }

    fun selectBlue(view: View) {
        setBackgroundColor(BackgroundColor.BLUE)
        handleColorChange()
    }

    fun selectPurple(view: View) {
        setBackgroundColor(BackgroundColor.PURPLE)
        handleColorChange()
    }

    fun selectYellow(view: View) {
        setBackgroundColor(BackgroundColor.YELLOW)
        handleColorChange()
    }

    fun selectGreen(view: View) {
        setBackgroundColor(BackgroundColor.GREEN)
        handleColorChange()
    }

    fun selectAqua(view: View) {
        setBackgroundColor(BackgroundColor.AQUA)
        handleColorChange()
    }

    fun onClickButtonSettingsBack(view: View) {
        finish()
    }


}