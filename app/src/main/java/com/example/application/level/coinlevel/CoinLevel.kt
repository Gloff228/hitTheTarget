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

var conSpeed: Int = -1

class CoinLevel: AbstractLevelActivity() {

    private lateinit var coinList: MutableList<FigureCoin>
    lateinit var currentCoin: FigureCoin
    var coinCounter = 0
    var figuresNumber: Int = -1
    var figureSize: Int = -1

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coinlevel)

        getSettings()
        setCounter()
        createImage()

        surface = findViewById(R.id.coinSurface)
        surface.holder.addCallback(this)
        surface.setZOrderOnTop(true)
        surface.holder.setFormat(PixelFormat.TRANSPARENT)

        if (coinCounter == figuresNumber) finishLevel()
    }

    private fun createImage() {
        val coin1 = BitmapFactory.decodeResource(this.resources, R.drawable.coin1)
        val coin2 = BitmapFactory.decodeResource(this.resources, R.drawable.coin2)
        val coin3 = BitmapFactory.decodeResource(this.resources, R.drawable.coin5)
        val coin4 = BitmapFactory.decodeResource(this.resources, R.drawable.coin10)
        val coin5 = BitmapFactory.decodeResource(this.resources, R.drawable.coin50)

        coinList = mutableListOf(
            FigureCoin(Bitmap.createScaledBitmap(coin1, figureSize, figureSize, false)),
            FigureCoin(Bitmap.createScaledBitmap(coin2, figureSize, figureSize, false)),
            FigureCoin(Bitmap.createScaledBitmap(coin3, figureSize, figureSize, false)),
            FigureCoin(Bitmap.createScaledBitmap(coin4, figureSize, figureSize, false)),
            FigureCoin(Bitmap.createScaledBitmap(coin5, figureSize, figureSize, false)),
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

    private fun getSettings() {
        figuresNumber = intent.getIntExtra(CoinSettings.PARAM_FIGURES_NUMBER, 1)
        figureSize = intent.getIntExtra(CoinSettings.PARAM_FIGURE_SIZE, 1)
        com.example.application.level.coinlevel.conSpeed = intent.getIntExtra(CoinSettings.PARAM_SPEED, 1)
    }

    @SuppressLint("SetTextI18n")
    fun setCounter() {
        val textOfCounter = findViewById<TextView>(R.id.counterText)
        textOfCounter.text = "0/${figuresNumber}"
    }

    @SuppressLint("SetTextI18n")
    private fun updateCounter() {
        val textOfCounter = findViewById<TextView>(R.id.counterText)

        coinCounter++
        textOfCounter.text = "${coinCounter}/${figuresNumber}"
        if (coinCounter == figuresNumber) finishLevel()
    }

    override fun finishLevel() {
        val intent = Intent(this, CoinLevelResultActivity::class.java)
        intent.putExtra(CoinSettings.PARAM_FIGURES_NUMBER, figuresNumber)
        intent.putExtra(CoinSettings.PARAM_FIGURE_SIZE, figureSize)
        intent.putExtra(
            CoinSettings.PARAM_SPEED,
            com.example.application.level.coinlevel.conSpeed
        )
        startActivity(intent)
        finish()
    }

    fun onClickReturnButton(view: View) {
        finish()
    }
}