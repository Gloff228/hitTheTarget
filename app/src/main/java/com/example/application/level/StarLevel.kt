package com.example.application.level

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.application.MyActivity
import com.example.application.R
import com.example.application.utils.starSettings
import kotlin.math.roundToInt
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

    init {
        NEED_HANDLE_DARK_MODE = false
    }

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

                override fun onAnimationEnd(animation: Animation?) {

                }
            })
        }
        star.startAnimation(anim)

        star.setOnClickListener {
            anim.cancel()
            star.scaleX = 0f // новый масштаб по X
            star.scaleY = 0f // новый масштаб по Y
            star.visibility = View.GONE
            currentStar--
            if (currentStar >= 0) {
                createAnimation(stars[currentStar])
            } else {
                finish()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createCountSeekBar() {
        val countSeekBar = findViewById<SeekBar>(R.id.seekBar)
        countSeekBar.min = 10
        countSeekBar.max = 30
        countSeekBar.progress = starSettings.maxCount

        val countTextView = findViewById<TextView>(R.id.textView1)
        countTextView.text = "${countSeekBar.progress} звёзд"

        countSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                countTextView.text = "$progress звёзд"
                starSettings.maxCount = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createSizeSeekBar() {
        val sizeSeekBar = findViewById<SeekBar>(R.id.seekBar2)
        sizeSeekBar.min = 50
        sizeSeekBar.max = 150
        sizeSeekBar.progress = starSettings.size

        val countTextView = findViewById<TextView>(R.id.textView2)
        countTextView.text = "${sizeSeekBar.progress}"

        sizeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val prg = (progress.toDouble() / 10).roundToInt() * 10
                countTextView.text = "$prg"
                starSettings.size = prg
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createScaleSeekBar() {
        val scaleSeekBar = findViewById<SeekBar>(R.id.seekBar3)
        scaleSeekBar.min = 2
        scaleSeekBar.max = 4
        scaleSeekBar.progress = starSettings.scale.toInt()

        val countTextView = findViewById<TextView>(R.id.textView3)
        countTextView.text = "в ${scaleSeekBar.progress} раза"

        scaleSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                countTextView.text = "в $progress раза"
                starSettings.scale = progress.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createDurationSeekBar() {
        val durationSeekBar = findViewById<SeekBar>(R.id.seekBar4)
        durationSeekBar.min = 1
        durationSeekBar.max = 2000
        durationSeekBar.progress = starSettings.duration.toInt()

        val countTextView = findViewById<TextView>(R.id.textView4)
        countTextView.text =
            "${Math.round(durationSeekBar.progress.toDouble() / 100).toDouble() / 10} сек"

        durationSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val prg = Math.round(progress.toDouble() / 100).toDouble() / 10
                countTextView.text = "$prg сек"
                if (prg == 0.0) {
                    starSettings.duration = 1
                } else {
                    starSettings.duration = (prg * 1000).toLong()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_star_level)
        createCountSeekBar()
        createSizeSeekBar()
        createScaleSeekBar()
        createDurationSeekBar()
    }

    fun onClickButtonCreate(view: View) {
        findViewById<ScrollView>(R.id.scrollView).visibility = View.GONE
        findViewById<ImageView>(R.id.returnButton).visibility = View.GONE
        drawStars()
    }

    fun onClickReturnButton(view: View) {
        finish()
    }
}