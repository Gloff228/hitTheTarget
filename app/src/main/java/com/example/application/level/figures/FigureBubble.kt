package com.example.application.level.figures

import android.content.res.Resources
import android.graphics.*
import com.example.application.level.bubblelevel.coefficientForSpeed

class FigureBubble(img: Bitmap): FigureBase() {

    private var bubbleSpeed = 5 + coefficientForSpeed
    private val bubble = img

    init {
        resetPosition()
    }

    fun getBubble() = bubble

    private fun getBubbleWidth() = bubble.width

    fun getBubbleHeight() = bubble.height

    private fun getRandomNumber(range: IntRange) =
        kotlin.math.floor(Math.random() * (range.last - range.first + 1))

    private fun arrangeX() =
        (getRandomNumber(getBubbleWidth()..Resources.getSystem().displayMetrics.widthPixels-getBubbleWidth())).toFloat()

    override fun draw(canvas: Canvas) {
        canvas.drawBitmap(getBubble(), x.toFloat(), y.toFloat(), null)
    }

    fun move() {
        y -= bubbleSpeed
    }

    fun resetPosition() {
        y = Resources.getSystem().displayMetrics.heightPixels.toDouble()
        x = arrangeX().toDouble()
    }
}