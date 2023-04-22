package com.example.application.level

import android.os.Bundle
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import com.example.application.MyActivity
import com.example.application.R
import kotlin.math.floor

class BubbleLevel: MyActivity() {

    private fun getRandomNumber(range: IntRange) = floor(Math.random() * (range.last - range.first + 1))
    private val countBurstBubbles = 20

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bubblelevel)

        val heightScreen = resources.displayMetrics.heightPixels
        val widthScreen = resources.displayMetrics.widthPixels
        val imageView = findViewById<ImageView>(R.id.imageView)
        val animator = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, heightScreen.toFloat(), 0f)


        fun getRandomDrawableNumber(): Int {
            val array = arrayOf(
                R.drawable.bubble_first, R.drawable.bubble_second, R.drawable.bubble_third,
                R.drawable.bubble_fourth, R.drawable.bubble_fifth
            )
            return array[getRandomNumber(array.indices).toInt()]
        }

        fun getImage() {
            return imageView.setImageResource(getRandomDrawableNumber())
        }

        fun arrangeX() {
            imageView.x = (getRandomNumber(150..widthScreen-150)).toFloat()
        }


        fun animate(i: Int) {
            animator.duration = 3000
            animator.start()

            imageView.setOnClickListener {
                animator.end()
                if (i < countBurstBubbles - 1)
                    animate(i+1)
                else animator.cancel()
            }

            getImage()
            arrangeX()
        }

        animate(0)
    }
}