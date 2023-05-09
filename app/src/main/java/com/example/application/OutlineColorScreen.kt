package com.example.application

import android.os.Bundle
import android.view.View
import com.example.application.utils.TargetColor

class OutlineColorScreen : MyActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_outline_color_screen)
    }

    fun selectWhite(view: View) {
        setOutlineColor(TargetColor.WHITE)
    }

    fun selectGray(view: View) {
        setOutlineColor(TargetColor.GRAY)
    }

    fun selectPink(view: View) {
        setOutlineColor(TargetColor.PINK)
    }

    fun selectRed(view: View) {
        setOutlineColor(TargetColor.RED)
    }

    fun selectBlue(view: View) {
        setOutlineColor(TargetColor.BLUE)
    }

    fun selectPurple(view: View) {
        setOutlineColor(TargetColor.PURPLE)
    }

    fun selectYellow(view: View) {
        setOutlineColor(TargetColor.YELLOW)
    }

    fun selectGreen(view: View) {
        setOutlineColor(TargetColor.GREEN)
    }

    fun onClickButtonSettingsBack(view: View) {
        finish()
    }
}