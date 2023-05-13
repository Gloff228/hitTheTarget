package com.example.application.level.bubblelevel

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.*
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.application.R
import com.example.application.level.AbstractLevelActivity
import com.example.application.level.figures.FigureBubble

var bubSpeed: Int = -1

class BubbleLevel: AbstractLevelActivity() {

    private lateinit var bubbleList: MutableList<FigureBubble>
    lateinit var currentBubble: FigureBubble
    var isRunning = true
    var bubbleCounter = 0
    var figuresNumber: Int = -1
    var figureSize: Int = -1

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bubblelevel)

        getSettings()
        setCounter()
        createImage()

        surface = findViewById(R.id.bubSurface)
        surface.holder.addCallback(this)
        surface.setZOrderOnTop(true)
        surface.holder.setFormat(PixelFormat.TRANSPARENT)

        if (bubbleCounter == figuresNumber) finishLevel()
    }

    private fun createImage() {
        val bubble1 = BitmapFactory.decodeResource(this.resources, R.drawable.bubble_first)
        val bubble2 = BitmapFactory.decodeResource(this.resources, R.drawable.bubble_second)
        val bubble3 = BitmapFactory.decodeResource(this.resources, R.drawable.bubble_third)
        val bubble4 = BitmapFactory.decodeResource(this.resources, R.drawable.bubble_fourth)
        val bubble5 = BitmapFactory.decodeResource(this.resources, R.drawable.bubble_fifth)

        bubbleList = mutableListOf(
            FigureBubble(Bitmap.createScaledBitmap(bubble1, figureSize, figureSize, false)),
            FigureBubble(Bitmap.createScaledBitmap(bubble2, figureSize, figureSize, false)),
            FigureBubble(Bitmap.createScaledBitmap(bubble3, figureSize, figureSize, false)),
            FigureBubble(Bitmap.createScaledBitmap(bubble4, figureSize, figureSize, false)),
            FigureBubble(Bitmap.createScaledBitmap(bubble5, figureSize, figureSize, false)),
        )
    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        val drawingThread = Thread {
            while (isRunning) {
                try {
                    drawNewFrame(surfaceHolder)
                    Thread.sleep(5)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        drawingThread.start()
    }

    private fun drawNewFrame(holder: SurfaceHolder) {
        val canvas = holder.lockCanvas()
        currentBubble = bubbleList[0]

        if (canvas != null) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
            currentBubble.move()

            if (currentBubble.y < -currentBubble.getBubbleHeight()) {
                resetPosition()
            } else currentBubble.draw(canvas)
        }
        holder.unlockCanvasAndPost(canvas)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event?.action == MotionEvent.ACTION_DOWN) {
            val bitmap = currentBubble.getBubble()

            val x = event.x.toInt()
            val y = event.y.toInt()

            val bitmapX = x - currentBubble.x
            val bitmapY = y - currentBubble.y

            if (bitmapX >= 0 && bitmapX < bitmap.width && bitmapY >= 0 && bitmapY < bitmap.height) {
                val pixel = bitmap.getPixel(bitmapX.toInt(), bitmapY.toInt())

                if (Color.alpha(pixel) != 0) {
                    resetPosition()
                    updateCounter()
                    return true
                }
            }
        }
        return false
    }

    private fun resetPosition() {
        currentBubble.resetPosition()
        bubbleList.add(currentBubble)
        bubbleList.removeAt(0)
    }

    private fun getSettings() {
        figuresNumber = intent.getIntExtra(BubbleSettings.PARAM_FIGURES_NUMBER, 1)
        figureSize = intent.getIntExtra(BubbleSettings.PARAM_FIGURE_SIZE, 1)
        bubSpeed = intent.getIntExtra(BubbleSettings.PARAM_SPEED, 1)
    }

    @SuppressLint("SetTextI18n")
    fun setCounter() {
        val textOfCounter = findViewById<TextView>(R.id.counterText)
        textOfCounter.text = "0/${figuresNumber}"
    }

    @SuppressLint("SetTextI18n")
    private fun updateCounter() {
        val textOfCounter = findViewById<TextView>(R.id.counterText)

        bubbleCounter++
        textOfCounter.text = "${bubbleCounter}/${figuresNumber}"
        if (bubbleCounter == figuresNumber) finishLevel()

    }

    override fun finishLevel() {
        isRunning = false
        val intent = Intent(this, BubbleLevelResultActivity::class.java)
        intent.putExtra(BubbleSettings.PARAM_FIGURES_NUMBER, figuresNumber)
        intent.putExtra(BubbleSettings.PARAM_FIGURE_SIZE, figureSize)
        intent.putExtra(BubbleSettings.PARAM_SPEED, bubSpeed)
        startActivity(intent)
        finish()
    }

    fun onClickReturnButton(view: View) {
        isRunning = false
        finish()
    }
}