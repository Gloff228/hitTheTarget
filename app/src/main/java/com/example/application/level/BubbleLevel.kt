package com.example.application.level

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.example.application.R
import com.example.application.level.figures.FigureBubble


class BubbleLevel: AbstractLevelActivity() {

    private lateinit var bubbleList: MutableList<FigureBubble>
    lateinit var currentBubble: FigureBubble


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bubblelevel)

        surface = findViewById(R.id.bubSurface)
        surface.holder.addCallback(this)
    }

    override fun onResume() {
        super.onResume()

        val bubble1 = BitmapFactory.decodeResource(this.resources, R.drawable.bubble_first)
        val bubble2 = BitmapFactory.decodeResource(this.resources, R.drawable.bubble_second)
        val bubble3 = BitmapFactory.decodeResource(this.resources, R.drawable.bubble_third)
        val bubble4 = BitmapFactory.decodeResource(this.resources, R.drawable.bubble_fourth)
        val bubble5 = BitmapFactory.decodeResource(this.resources, R.drawable.bubble_fifth)

        bubbleList = mutableListOf(
            FigureBubble(Bitmap.createScaledBitmap(bubble1, 350, 350, false)),
            FigureBubble(Bitmap.createScaledBitmap(bubble2, 350, 350, false)),
            FigureBubble(Bitmap.createScaledBitmap(bubble3, 350, 350, false)),
            FigureBubble(Bitmap.createScaledBitmap(bubble4, 350, 350, false)),
            FigureBubble(Bitmap.createScaledBitmap(bubble5, 350, 350, false)),
        )
    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        canRun = true
        Thread {
            while (canRun) {
                draw(surfaceHolder)
                Thread.sleep(5)
            }
        }.start()
    }

    private fun draw(holder: SurfaceHolder) {
        val canvas = holder.lockCanvas()

        currentBubble = bubbleList[0]

        if (canvas != null) {
            canvas.drawColor(Color.rgb(217, 170, 252))
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
}