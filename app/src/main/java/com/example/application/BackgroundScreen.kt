package com.example.application

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.application.level.figures.FigureComCircle
import com.example.application.level.figures.FigureSettingCircle
import com.example.application.utils.BackgroundColor

class BackgroundScreen : MyActivity() {
    class MyView(context: Context) : View(context) {
        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            val figure = FigureSettingCircle(canvas.height)
            figure.setPosition(canvas.height/2, canvas.height/2)
            figure.draw(canvas)
        }
    }
    private fun redraw() {
        val myView = MyView(this)
        val layout = findViewById<LinearLayout>(R.id.circle)
        layout.removeAllViews()
        layout.addView(myView)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background_screen)
        redraw()
    }

    override fun onStop() {
        super.onStop()

        saveGlobalSettings()
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