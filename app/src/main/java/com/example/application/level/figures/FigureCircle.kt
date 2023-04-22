package com.example.application.level.figures

import android.graphics.Canvas
import com.example.application.level.utils.Dot
import kotlin.math.pow

open class FigureCircle(
    diameter: Number,
) : FigureBase() {

    var diameter = diameter.toDouble()
    var radius = this.diameter / 2
    var center = Dot(x + radius, y + radius)

    override fun onClickEvent() {
        if (isActive) {
            this.delete()
            println("Deleted")
        }
    }

    override fun setPosition(x: Number, y: Number) {
        super.setPosition(x, y)
        center = Dot(this.x + radius, this.y + radius)
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
        // TODO it is bad solution. Need to update for outlines
        var radiusToDraw = radius
        if (!isActive) {
             radiusToDraw *= 0.5
        }
        canvas.drawCircle(center.x.toFloat(), center.y.toFloat(), radiusToDraw.toFloat(), paint)
    }
}