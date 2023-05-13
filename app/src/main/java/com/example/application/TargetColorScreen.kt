package com.example.application

import android.content.Context
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.example.application.level.figures.FigureComCircle
import com.example.application.level.figures.FigureSettingCircle
import com.example.application.utils.TargetColor
import com.example.application.utils.globalSettings
class TargetColorScreen : MyActivity() {
    class MyView(context: Context) : View(context) {
        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            val figure = FigureSettingCircle(height)
            figure.setPosition(height/2, height/2)
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
        setContentView(R.layout.activity_target_color_screen)

        redraw()
    }

    override fun onStop() {
        super.onStop()

        saveGlobalSettings()
    }

    fun selectWhite(view: View) {
        setTargetColor(TargetColor.WHITE)
        redraw()
    }

    fun selectBlack(view: View) {
        setTargetColor(TargetColor.BLACK)
        redraw()
    }

    fun selectPink(view: View) {
        setTargetColor(TargetColor.PINK)
        redraw()
    }

    fun selectRed(view: View) {
        setTargetColor(TargetColor.RED)
        redraw()
    }

    fun selectBlue(view: View) {
        setTargetColor(TargetColor.BLUE)
        redraw()
    }

    fun selectPurple(view: View) {
        setTargetColor(TargetColor.PURPLE)
        redraw()
    }

    fun selectYellow(view: View) {
        setTargetColor(TargetColor.YELLOW)
        redraw()
    }

    fun selectGreen(view: View) {
        setTargetColor(TargetColor.GREEN)
        redraw()
    }

    fun selectDarkGreen(view: View) {
        setTargetColor(TargetColor.DARKGREEN)
        redraw()
    }

    fun onClickButtonSettingsBack(view: View) {
        finish()
    }


}