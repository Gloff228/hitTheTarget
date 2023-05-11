package com.example.application.level.figures

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import com.example.application.R
import com.example.application.level.utils.Dot


class FigureVegetable(
    private var vegetableImg: Bitmap,
    private var sproutImg: Bitmap,
) : FigureBase() {


    override fun onClickEvent() {
        if (isActive) {
            this.delete()
        }
    }

    override fun isPointInsideFigure(x: Number, y: Number): Boolean {
        val bitmap = this.vegetableImg
        val bitmapX = x.toInt() - this.x.toInt()
        val bitmapY = y.toInt() - this.y.toInt()

        if (bitmapX >= 0 && bitmapX < bitmap.width && bitmapY >= 0 && bitmapY < bitmap.height) {
            val pixel = bitmap.getPixel(bitmapX, bitmapY)
            if (Color.alpha(pixel) != 0) {
                return true
            }

        }
        return false

    }

    /*override fun calculateNewFrame(startTime: Long) {
        if (isActive) {
            if (size < 1) {
                val currentSide = (DEFAULT_IMAGE_SIDE * size).toInt()
                vegetableImg = resizeBitmap(vegetableImg, currentSide, currentSide)
                needRedraw = true
                size += 0.02
            } else needRedraw = false;
        }
    }*/


    override fun draw(canvas: Canvas) {
        val sproutX = (x + vegetableImg.width / 2).toFloat()
        val sproutY = (y + vegetableImg.height / 2).toFloat()
        if (!isActive)
            canvas.drawBitmap(sproutImg, sproutX , sproutY, paint)
        else
            canvas.drawBitmap(vegetableImg, x.toFloat(), y.toFloat(), paint)
    }


}