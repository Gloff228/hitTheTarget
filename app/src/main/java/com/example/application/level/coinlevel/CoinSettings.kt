package com.example.application.level.coinlevel

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import com.example.application.MyActivity
import com.example.application.R


internal var coefficientForSize = 0
internal var numberCoin = 0
var coefficientForSpeed = 0


class CoinSettings: MyActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coinsettings_screen)

        getInfFromSeekBarSize()
        getInfFromSeekBarCounter()
        getInfFromSeekBarSpeed()
    }

    private fun getInfFromSeekBarSize() {
        val txtOfSize = findViewById<TextView>(R.id.coinSize)
        val sliderForSize = findViewById<SeekBar>(R.id.seekBarSize)

        sliderForSize.setOnSeekBarChangeListener(object:
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                coefficientForSize = progress
                txtOfSize.text = coefficientForSize.toString()
            }


            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }

    private fun getInfFromSeekBarCounter() {
        val txtOfCounter = findViewById<TextView>(R.id.coinCounter)
        val sliderForCount = findViewById<SeekBar>(R.id.seekBarCounter)

        sliderForCount.setOnSeekBarChangeListener(object:
            SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                numberCoin = progress
                txtOfCounter.text = numberCoin.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }

    private fun getInfFromSeekBarSpeed() {
        val txtOfSpeed = findViewById<TextView>(R.id.coinSpeed)
        val sliderForSpeed = findViewById<SeekBar>(R.id.seekBarSpeed)

        sliderForSpeed.setOnSeekBarChangeListener(object:
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                coefficientForSpeed = progress
                txtOfSpeed.text = coefficientForSpeed.toString()
            }


            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }

    fun onPlayButtonClick(view: View) {
        startActivity(Intent(this, CoinLevel::class.java))
    }

    fun onClickReturnButton(view: View) {
        finish()
    }
}