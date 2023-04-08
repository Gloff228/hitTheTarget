package com.example.application.level.figures

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import com.example.application.level.utils.Dot
import kotlin.math.*

open class FigureStar(
    size: Number
) : FigureBase() {

    private val starSize = size.toDouble()
    private val radius = this.starSize / 2
    var center = Dot(x, y)

    private val starPaint = Paint().apply {
        color = Color.YELLOW
        style = Paint.Style.FILL
        isAntiAlias = true
    }


    override fun onClickEvent() {
        this.delete()
        println("Clicked")
    }

    override fun setPosition(x: Number, y: Number) {
        super.setPosition(x, y)
        center = Dot(this.x, this.y)
    }

    open fun getDistanceFromCenter(x: Number, y: Number): Double {
        val pointX = x.toDouble()
        val pointY = y.toDouble()

        return ((pointX - center.x).pow(2) + (pointY - center.y).pow(2)).pow(0.5)
    }

    override fun isPointInsideFigure(x: Number, y: Number): Boolean {
        return getDistanceFromCenter(x, y) <= radius
    }

    override fun draw(canvas: Canvas) {
        canvas.drawStar(this.x, this.y, starSize, starPaint)
    }

    private fun Canvas.drawStar(x: Double, y: Double, size: Double, starPaint: Paint) {
        this.drawCircle(center.x.toFloat(), center.y.toFloat(), radius.toFloat(), paint) // область, в которой будет засчитываться нажатие
        val path = Path()
        val degreesPerStep = 360 / 5
        val outerRadius = size / 2.0
        val innerRadius = size * 0.4 / 2.0
        var degrees = -90
        for (i in 0..4) {
            val outerX = (x + outerRadius * cos(Math.toRadians(degrees.toDouble()))).toFloat()
            val outerY = (y + outerRadius * sin(Math.toRadians(degrees.toDouble()))).toFloat()
            val innerX = (x + innerRadius * cos(Math.toRadians((degrees + degreesPerStep / 2).toDouble()))).toFloat()
            val innerY = (y + innerRadius * sin(Math.toRadians((degrees + degreesPerStep / 2).toDouble()))).toFloat()
            if (i == 0) {
                path.moveTo(outerX, outerY)
            } else {
                path.lineTo(outerX, outerY)
            }
            path.lineTo(innerX, innerY)
            degrees += degreesPerStep
        }
        path.close()
        drawPath(path, starPaint)
    }


}