package com.example.application

import android.os.Bundle
import android.view.View
import com.example.application.utils.TargetColor

class TargetColorScreen : MyActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_target_color_screen)
    }

    override fun onStop() {
        super.onStop()

        saveGlobalSettings()
    }

    fun selectWhite(view: View) {
        setTargetColor(TargetColor.WHITE)
    }

    fun selectBlack(view: View) {
        setTargetColor(TargetColor.BLACK)
    }

    fun selectPink(view: View) {
        setTargetColor(TargetColor.PINK)
    }

    fun selectRed(view: View) {
        setTargetColor(TargetColor.RED)
    }

    fun selectBlue(view: View) {
        setTargetColor(TargetColor.BLUE)
    }

    fun selectPurple(view: View) {
        setTargetColor(TargetColor.PURPLE)
    }

    fun selectYellow(view: View) {
        setTargetColor(TargetColor.YELLOW)
    }

    fun selectGreen(view: View) {
        setTargetColor(TargetColor.GREEN)
    }

    fun selectDarkGreen(view: View) {
        setTargetColor(TargetColor.DARKGREEN)
    }

    fun onClickButtonSettingsBack(view: View) {
        finish()
    }


}