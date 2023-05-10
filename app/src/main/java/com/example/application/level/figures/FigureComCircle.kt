package com.example.application.level.figures

import android.graphics.Canvas
import com.example.application.level.CommonLevelActivity
import com.example.application.utils.TargetColor
import com.example.application.utils.globalSettings
import kotlin.math.max
import kotlin.math.min

open class FigureComCircle(
    diameter: Number,
    additionalZonePercent: Double = 0.2,
    private val outlinePercent: Double = 0.05
): FigureEasyCircle(diameter, additionalZonePercent) {

    private val outlineColor = globalSettings.outlineColor.rgb.toInt()
    private val outlineMinSize = 3.0
    private val outlineMaxSize = 30.0

    override fun draw(canvas: Canvas) {
        var radiusToDraw = radius
        val outlineSize: Double
        if (!isActive) {
            radiusToDraw *= 0.3
            outlineSize = outlineMinSize
        } else {
            outlineSize = min(max(radiusToDraw * outlinePercent, outlineMinSize), outlineMaxSize)
        }

        val initialPaintColor = paint.color

        paint.color = outlineColor
        canvas.drawCircle(center.x.toFloat(), center.y.toFloat(), radiusToDraw.toFloat(), paint)
        paint.color = initialPaintColor
        canvas.drawCircle(center.x.toFloat(), center.y.toFloat(), (radiusToDraw - outlineSize).toFloat(), paint)
    }
}