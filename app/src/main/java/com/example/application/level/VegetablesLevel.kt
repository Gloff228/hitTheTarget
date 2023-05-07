package com.example.application.level

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PixelFormat
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.application.R
import com.example.application.level.figures.FigureVegetable


class VegetablesLevel : AbstractLevelActivity() {

    var figuresNumber: Int = 10 //TODO get from settings

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_vegetables_level)

        surface = findViewById(R.id.vegetableSurface)
        surface.holder.addCallback(this)
        surface.setZOrderOnTop(true)
        surface.holder.setFormat(PixelFormat.TRANSPARENT)
        createLevel()
    }

    private fun createLevel() {
        figures = ArrayDeque(figuresNumber)
        for (i in 1..figuresNumber) {

            val listOfVegetable = mutableListOf(
                BitmapFactory.decodeResource(this.resources, R.drawable.pumpkin),
                BitmapFactory.decodeResource(this.resources, R.drawable.cucumber),
                BitmapFactory.decodeResource(this.resources, R.drawable.tomato),
            )


            val vegetableImg = listOfVegetable.random()
            val sproutImage = BitmapFactory.decodeResource(this.resources, R.drawable.sprout)

            val side = FigureVegetable(vegetableImg, sproutImage).getDefaultSide().toInt()
            val vegetable = Bitmap.createScaledBitmap(vegetableImg, side, side, false)
            val sprout = Bitmap.createScaledBitmap(sproutImage, 90, 90, false)
            val figure = FigureVegetable(vegetable, sprout)
            figure.bindLevel(this)

            figures.addLast(figure)
        }
        figures.last().setActive()
    }

    override fun setBackgroundColor(canvas: Canvas) {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
    }

    override fun finishLevel() {
        // TODO show finish modal (with replay level button or exit)
        finish()
    }


}