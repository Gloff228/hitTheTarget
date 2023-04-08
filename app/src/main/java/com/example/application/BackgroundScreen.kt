package com.example.application

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.application.utils.BackgroundColor
import com.example.application.utils.globalSettings

class BackgroundScreen : MyActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background_screen)
    }

    fun selectWhite(view: View) {
        setBackgroundColor(BackgroundColor.WHITE)
    }

    fun selectGray(view: View) {
        setBackgroundColor(BackgroundColor.GRAY)
    }

    fun selectPink(view: View) {
        setBackgroundColor(BackgroundColor.PINK)
    }

    fun selectRed(view: View) {
        setBackgroundColor(BackgroundColor.RED)
    }

    fun selectBlue(view: View) {
        setBackgroundColor(BackgroundColor.BLUE)
    }

    fun selectPurple(view: View) {
        setBackgroundColor(BackgroundColor.PURPLE)
    }

    fun onClickButtonSettingsBack(view: View) {
        finish()
    }

}