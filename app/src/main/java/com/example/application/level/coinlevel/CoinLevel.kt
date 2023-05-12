package com.example.application.level.coinlevel

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
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
import com.example.application.level.figures.FigureCoin

class CoinLevel: AbstractLevelActivity() {
    private lateinit var coinList: MutableList<FigureCoin>
    lateinit var currentCoin: FigureCoin
    private val coinSize = 4 * coefficientForSize + 350
    var coinCounter = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coinlevel)

        setCounter()
        surface = findViewById(R.id.coinSurface)
        surface.holder.addCallback(this)
        surface.setZOrderOnTop(true)
        surface.holder.setFormat(PixelFormat.TRANSPARENT)

    }

    override fun onResume() {
        super.onResume()

        val coin1 = BitmapFactory.decodeResource(this.resources, R.drawable.coin1)
        val coin2 = BitmapFactory.decodeResource(this.resources, R.drawable.coin2)
        val coin3 = BitmapFactory.decodeResource(this.resources, R.drawable.coin5)
        val coin4 = BitmapFactory.decodeResource(this.resources, R.drawable.coin10)
        val coin5 = BitmapFactory.decodeResource(this.resources, R.drawable.coin50)

        coinList = mutableListOf(
            FigureCoin(Bitmap.createScaledBitmap(coin1, coinSize, coinSize, false)),
            FigureCoin(Bitmap.createScaledBitmap(coin2, coinSize, coinSize, false)),
            FigureCoin(Bitmap.createScaledBitmap(coin3, coinSize, coinSize, false)),
            FigureCoin(Bitmap.createScaledBitmap(coin4, coinSize, coinSize, false)),
            FigureCoin(Bitmap.createScaledBitmap(coin5, coinSize, coinSize, false)),
        )
    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        canRun = true
        Thread {
            while (canRun) {
                try {
                    drawNewFrame(surfaceHolder)
                    Thread.sleep(5)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    private fun drawNewFrame(holder: SurfaceHolder) {
        val canvas = holder.lockCanvas()

        currentCoin = coinList[0]

        if (canvas != null) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
            currentCoin.move()

            if (currentCoin.y > Resources.getSystem().displayMetrics.heightPixels) {
                resetPosition()
            } else currentCoin.draw(canvas)
        }
        holder.unlockCanvasAndPost(canvas)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event?.action == MotionEvent.ACTION_DOWN) {
            val bitmap = currentCoin.getCoin()

            val x = event.x.toInt()
            val y = event.y.toInt()

            val bitmapX = x - currentCoin.x
            val bitmapY = y - currentCoin.y

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
        currentCoin.resetPosition()
        coinList.add(currentCoin)
        coinList.removeAt(0)
    }

    @SuppressLint("SetTextI18n")
    fun setCounter() {
        val textOfCounter = findViewById<TextView>(R.id.counterText)
        textOfCounter.text = "0/$numberCoin"
    }

    @SuppressLint("SetTextI18n")
    private fun updateCounter() {
        val textOfCounter = findViewById<TextView>(R.id.counterText)

        if (coinCounter < numberCoin - 1) {
            coinCounter++
            textOfCounter.text = "${coinCounter}/$numberCoin"
        } else finishLevel()
    }

    override fun finishLevel() {
        startActivity(Intent(this, CoinVictoryScreen::class.java))
        finish()
    }

    fun onClickReturnButton(view: View) {
        finish()
    }
}