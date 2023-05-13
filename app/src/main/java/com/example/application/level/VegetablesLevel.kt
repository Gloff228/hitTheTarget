package com.example.application.level

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PixelFormat
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.application.R
import com.example.application.level.figures.FigureVegetable
import com.example.application.level.utils.Dot
import kotlin.random.Random


class VegetablesLevel : AbstractLevelActivity() {

    var figuresNumber: Int = -1
    var figureSize: Int = -1

    lateinit var scoreView: TextView



    private fun isPointInsideTrapezoid(d1: Dot, d2: Dot, d3: Dot, d4: Dot, point: Dot): Boolean {
        val b1 = (point.y - d1.y) * (d2.x - d1.x) > (point.x - d1.x) * (d2.y - d1.y)
        val b2 = (point.y - d2.y) * (d3.x - d2.x) > (point.x - d2.x) * (d3.y - d2.y)
        val b3 = (point.y - d3.y) * (d4.x - d3.x) > (point.x - d3.x) * (d4.y - d3.y)
        val b4 = (point.y - d4.y) * (d1.x - d4.x) > (point.x - d4.x) * (d1.y - d4.y)

        return (b1 == b2) && (b2 == b3) && (b3 == b4)
    }

    private fun isPointInsideFirstGarden(point: Dot): Boolean {
        val d1 = Dot(0, 0.8 * HEIGHT)
        val d2 = Dot(0.2 * WIDTH, 0.8 * HEIGHT)
        val d3 = Dot(0.35 * WIDTH, 0.4 * HEIGHT)
        val d4 = Dot(0.15 * WIDTH, 0.4 * HEIGHT)
        return isPointInsideTrapezoid(d1, d2, d3, d4, point)
    }

    private fun isPointInsideSecondGarden(point: Dot): Boolean {
        val d1 = Dot(0.4 * WIDTH, 0.8 * HEIGHT)
        val d2 = Dot(0.65 * WIDTH, 0.8 * HEIGHT)
        val d3 = Dot(0.60 * WIDTH, 0.4 * HEIGHT)
        val d4 = Dot(0.45 * WIDTH, 0.4 * HEIGHT)
        return isPointInsideTrapezoid(d1, d2, d3, d4, point)
    }

    private fun isPointInsideThirdGarden(point: Dot): Boolean {
        val d1 = Dot(0.80 * WIDTH, 0.8 * HEIGHT)
        val d2 = Dot(WIDTH, 0.8 * HEIGHT)
        val d3 = Dot(0.85 * WIDTH, 0.4 * HEIGHT)
        val d4 = Dot(0.65 * WIDTH, 0.4 * HEIGHT)
        return isPointInsideTrapezoid(d1, d2, d3, d4, point)
    }

    private fun generateRandomPoint(
        leftBorder: Int,
        rightBorder: Int,
        isPointInside: (input: Dot) -> Boolean
    ): Dot {
        val topBorder = (HEIGHT * 0.2).toInt()
        val bottomBorder = (HEIGHT * 0.8).toInt()

        var x = Random.nextInt(leftBorder, rightBorder)
        var y = Random.nextInt(topBorder, bottomBorder)
        var point = Dot(x, y)

        while (!isPointInside(point)) {
            x = Random.nextInt(leftBorder, rightBorder)
            y = Random.nextInt(topBorder, bottomBorder)
            point = Dot(x, y)
        }

        return point
    }

    private fun generateRandomPointFirstGarden(): Dot {
        val leftBorder = (WIDTH * -0.1).toInt()
        val rightBorder = (WIDTH * 0.4).toInt()
        return generateRandomPoint(leftBorder, rightBorder, ::isPointInsideFirstGarden)
    }

    private fun generateRandomPointSecondGarden(): Dot {
        val leftBorder = (WIDTH * 0.2).toInt()
        val rightBorder = (WIDTH * 0.7).toInt()
        return generateRandomPoint(leftBorder, rightBorder, ::isPointInsideSecondGarden)
    }

    private fun generateRandomPointThirdGarden(): Dot {
        val leftBorder = (WIDTH * 0.5).toInt()
        val rightBorder = (WIDTH * 1.1).toInt()
        return generateRandomPoint(leftBorder, rightBorder, ::isPointInsideThirdGarden)
    }

    private fun generateRandomPosition(index: Int): Dot {
        var point = Dot(0, 0)
        when (index) {
            1 -> point = generateRandomPointFirstGarden()
            2 -> point = generateRandomPointSecondGarden()
            0 -> point = generateRandomPointThirdGarden()
        }
        return point
    }

    private fun createLevel() {
        figures = ArrayDeque(figuresNumber)
        for (i in 1..figuresNumber) {

            val listOfVegetable = mutableListOf(
                BitmapFactory.decodeResource(this.resources, R.drawable.carrot),
                BitmapFactory.decodeResource(this.resources, R.drawable.pumpkin),
                BitmapFactory.decodeResource(this.resources, R.drawable.tomato),
                BitmapFactory.decodeResource(this.resources, R.drawable.watermelon),
            )
            val sproutImage = BitmapFactory.decodeResource(this.resources, R.drawable.sprout)
            val vegetableImg = listOfVegetable.random()

            val vegetable = Bitmap.createScaledBitmap(vegetableImg, figureSize, figureSize, false)
            val sprout = Bitmap.createScaledBitmap(sproutImage, 90, 90, false)

            //val position = generateRandomPosition(i % 3)
            val position = Dot(Random.nextInt(0, WIDTH),
                Random.nextInt(0, HEIGHT))
            val figure = FigureVegetable(vegetable, sprout)
            figure.setPosition(position.x - figureSize / 2, position.y - figureSize / 2)
            figure.bindLevel(this)

            figures.addLast(figure)
        }
        updateScore()
        figures.last().setActive()
    }

    override fun onStart() {
        super.onStart()

        createLevel()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handleInfoViews()
        getSettings()

        surface = findViewById(R.id.surface)
        surface.holder.addCallback(this)
        surface.setZOrderOnTop(true)
        surface.holder.setFormat(PixelFormat.TRANSPARENT)
    }

    private fun getSettings() {
        figuresNumber = intent.getIntExtra(CommonLevelSettingsActivity.PARAM_FIGURES_NUMBER, -1)
        figureSize = intent.getIntExtra(CommonLevelSettingsActivity.PARAM_FIGURE_SIZE, -1)
    }

    override fun setBackgroundColor(canvas: Canvas) {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
    }

    override fun loadContentView() {
        setContentView(R.layout.activity_vegetables_level)
    }

    private fun handleInfoViews() {
        scoreView = findViewById(R.id.score)
    }

    @SuppressLint("SetTextI18n")
    fun updateScore() {
        scoreView.text = "${figures.size}/${figuresNumber}"
    }



    override fun setNewActiveFigure() {
        figures.removeLast()
        updateScore()
        if (figures.isNotEmpty()) {
            figures.shuffle()
            figures.last().setActive()
            needRedraw = true
        } else {
            finishLevel()
        }
    }

    private fun openResultScreen() {
        val intent = Intent(this, VegetableLevelResultActivity::class.java)
        intent.putExtra(CommonLevelSettingsActivity.PARAM_FIGURES_NUMBER, figuresNumber)
        intent.putExtra(CommonLevelSettingsActivity.PARAM_FIGURE_SIZE, figureSize)
        startActivity(intent)
    }

    override fun finishLevel() {
        if (!threadQuit) {
            threadQuit = true
            openResultScreen()
            finish()
        }
    }


}