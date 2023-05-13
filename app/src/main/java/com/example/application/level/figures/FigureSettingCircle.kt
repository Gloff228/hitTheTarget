package com.example.application.level.figures

import android.graphics.Canvas
import com.example.application.utils.globalSettings
import kotlin.math.max
import kotlin.math.min

class FigureSettingCircle(
    diameter: Number,
    private val outlinePercent: Double = 0.05
): FigureComCircle(
    diameter,
    0.0,
    outlinePercent,
) {
    private val outlineColor = globalSettings.outlineColor.rgb.toInt()
    private val outlineMinSize = 3.0
    private val outlineMaxSize = 30.0

    override fun draw(canvas: Canvas) {
        val outlineSize = min(max(radius * outlinePercent, outlineMinSize), outlineMaxSize)
        val initialPaintColor = paint.color

        paint.color = outlineColor
        canvas.drawCircle(center.x.toFloat(), center.y.toFloat(), radius.toFloat(), paint)
        paint.color = initialPaintColor
        canvas.drawCircle(center.x.toFloat(), center.y.toFloat(), (radius - outlineSize).toFloat(), paint)
    }

    override fun onClickEvent() {
        // pass
    }

    override fun onPressEvent() {
        // pass
    }

    override fun onReleaseEvent() {
        // pass
    }

    override fun delete() {
        // pass
    }
}