package com.example.application

import android.content.Context
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.example.application.level.figures.FigureComCircle
import com.example.application.level.figures.FigureSettingCircle
import com.example.application.utils.TargetColor

class OutlineColorScreen : MyActivity() {
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
        setContentView(R.layout.activity_outline_color_screen)
        redraw()
    }

    override fun onStop() {
        super.onStop()

        saveGlobalSettings()
    }

    fun selectWhite(view: View) {
        setOutlineColor(TargetColor.WHITE)
        redraw()
    }

    fun selectBlack(view: View) {
        setOutlineColor(TargetColor.BLACK)
        redraw()
    }

    fun selectPink(view: View) {
        setOutlineColor(TargetColor.PINK)
        redraw()
    }

    fun selectRed(view: View) {
        setOutlineColor(TargetColor.RED)
        redraw()
    }

    fun selectBlue(view: View) {
        setOutlineColor(TargetColor.BLUE)
        redraw()
    }

    fun selectPurple(view: View) {
        setOutlineColor(TargetColor.PURPLE)
        redraw()
    }

    fun selectYellow(view: View) {
        setOutlineColor(TargetColor.YELLOW)
        redraw()
    }

    fun selectGreen(view: View) {
        setOutlineColor(TargetColor.GREEN)
        redraw()
    }
    fun selectDarkGreen(view: View) {
        setOutlineColor(TargetColor.DARKGREEN)
        redraw()
    }
    fun onClickButtonSettingsBack(view: View) {
        finish()
    }
}