package com.example.application.level

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.application.MyActivity
import com.example.application.R
import com.example.application.utils.starSettings
import kotlin.random.Random

class StarLevel : MyActivity() {

    private var stars: MutableList<ImageView> = mutableListOf()
    private var currentStar: Int = 0
    private val starColours = mutableListOf(
        R.drawable.star_red,
        R.drawable.star_yellow,
        R.drawable.star_blue,
        R.drawable.star_green,
        R.drawable.star_orange,
        R.drawable.star_pink,
        R.drawable.star_purpule
    )

    private fun randomFloat(): Float {
        var randomFloat = Random.nextFloat()
        if (randomFloat < 0.07f) randomFloat = 0.07f
        else if (randomFloat > 0.93f) randomFloat = 0.93f
        return randomFloat
    }

    private fun createStar(size: Int): ImageView {
        return ImageView(this).apply {
            setImageResource(starColours.random())
            id = View.generateViewId()
            layoutParams = ConstraintLayout.LayoutParams(
                size,
                size
            ).apply {
                startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                horizontalBias = randomFloat()
                verticalBias = randomFloat()
                println(horizontalBias)
                println(verticalBias)
            }
        }
    }

    private fun drawStars() {
        currentStar = starSettings.maxCount - 1
        val layout = findViewById<ConstraintLayout>(R.id.layout)
        for (i in 0 until starSettings.maxCount) {
            val star = createStar(starSettings.size)
            layout.addView(star)
            stars.add(star)
        }
        createAnimation(stars[currentStar])
    }
    private fun createAnimation(star: ImageView) {
        val anim = ScaleAnimation(
            1f, starSettings.scale, 1f, starSettings.scale,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            duration = starSettings.duration
            repeatCount = 1
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}
                override fun onAnimationRepeat(animation: Animation?) {
                    star.scaleX = starSettings.scale // новый масштаб по X
                    star.scaleY = starSettings.scale // новый масштаб по Y
                    animation?.cancel()
                }

                override fun onAnimationEnd(animation: Animation?) {}
            })
        }
        star.startAnimation(anim)

        star.setOnClickListener {
            if (anim.hasEnded()) {
                star.visibility = View.GONE
                currentStar--
                if (currentStar >= 0) {
                    createAnimation(stars[currentStar])
                } else {
                    finish()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_star_level)

        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        seekBar.progress = 50 // устанавливаем начальное положение на середину
        seekBar.min = 3
        seekBar.max = 50

        val textView1 = findViewById<TextView>(R.id.textView1)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textView1.text = "Count\n$progress"
                starSettings.maxCount = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    fun onClickButtonCreate(view: View) { drawStars() }
}