package com.example.application.level.figures

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.application.level.bubblelevel.coefficientForSpeed

class FigureCoin(img: Bitmap): FigureBase() {
    private var coinSpeed = 5 + coefficientForSpeed
    private val coin = img

    init {
        resetPosition()
    }

    fun getCoin() = coin

    private fun getCoinWidth() = coin.width

    fun getCoinHeight() = coin.height

    private fun getRandomNumber(range: IntRange) =
        kotlin.math.floor(Math.random() * (range.last - range.first + 1))

    private fun arrangeX() =
        (getRandomNumber(getCoinWidth()..Resources.getSystem().displayMetrics.widthPixels-getCoinWidth())).toFloat()

    override fun draw(canvas: Canvas) {
        canvas.drawBitmap(getCoin(), x.toFloat(), y.toFloat(), null)
    }

    fun move() {
        y += coinSpeed
    }

    fun resetPosition() {
        y = - getCoinHeight().toDouble()
        x = arrangeX().toDouble()
    }
}