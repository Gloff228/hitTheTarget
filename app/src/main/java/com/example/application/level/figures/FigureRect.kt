package com.example.application.level.figures

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import kotlin.random.Random

open class FigureRect(
    private var width: Double,
    private var height: Double,
) : FigureBase() {

    override fun onClickEvent() {
        this.delete()
        println("Clicked")
    }

    override fun isPointInsideFigure(x: Number, y: Number): Boolean {
        val pointX = x.toDouble()
        val pointY = y.toDouble()
        return (this.x <= pointX && pointX <= this.x + width) &&
                (this.y <= pointY && pointY <= this.y + height)
    }

    override fun draw(canvas: Canvas) {
        canvas.drawRect(
            Rect(x.toInt(), y.toInt(), (x + width).toInt(), (y + height).toInt()),
            paint,
        )
    }
}