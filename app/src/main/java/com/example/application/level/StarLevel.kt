package com.example.application.level

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.application.MyActivity
import com.example.application.R
import kotlin.random.Random

class StarLevel : MyActivity() {
    var figureScale: Float = 3f
    var figuresNumber: Int = 20
    var figureSize: Int = 100
    var animationTime: Long = 1000
    lateinit var scoreView: TextView
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_star_level)
        scoreView = findViewById(R.id.score)

        getSettings()
        drawStars()
    }

    @SuppressLint("SetTextI18n")
    fun updateScore() {
        scoreView.text = "${currentStar}/${figuresNumber}"
    }

    private fun randomFloat(): Float {
        var randomFloat = Random.nextFloat()
        if (randomFloat < 0.07f) randomFloat = 0.07f
        else if (randomFloat > 0.93f) randomFloat = 0.93f
        return randomFloat
    }

    private fun getSettings() {
        figuresNumber = intent.getIntExtra(StarLevelSettings.PARAM_FIGURES_NUMBER, 20)
        figureSize = intent.getIntExtra(StarLevelSettings.PARAM_FIGURE_SIZE, 100)
        figureScale = intent.getIntExtra(StarLevelSettings.PARAM_FIGURE_SCALE, 3).toFloat()
        animationTime = intent.getIntExtra(StarLevelSettings.PARAM_ANIMATION_TIME, 1000).toLong()
        if (animationTime == 0L) {
            animationTime = 1
        }
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
        currentStar = figuresNumber
        updateScore()
        currentStar--
        val layout = findViewById<ConstraintLayout>(R.id.layout)
        for (i in 0 until figuresNumber) {
            val star = createStar(figureSize)
            layout.addView(star)
            stars.add(star)
        }
        createAnimation(stars[currentStar])
    }

    private fun createAnimation(star: ImageView) {
        val anim = ScaleAnimation(
            1f, figureScale, 1f, figureScale,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            duration = animationTime
            repeatCount = 1
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}
                override fun onAnimationRepeat(animation: Animation?) {
                    star.scaleX = figureScale // новый масштаб по X
                    star.scaleY = figureScale // новый масштаб по Y
                    animation?.cancel()
                }

                override fun onAnimationEnd(animation: Animation?) {

                }
            })
        }
        star.startAnimation(anim)

        star.setOnClickListener {
            anim.cancel()
            updateScore()
            star.scaleX = 0f
            star.scaleY = 0f
            star.visibility = View.GONE
            currentStar--
            if (currentStar >= 0) {
                createAnimation(stars[currentStar])
            } else {
                finishLevel()
            }
        }
    }

    private fun finishLevel() {
        val intent = Intent(this, StarLevelResultActivity::class.java)
        intent.putExtra(StarLevelSettings.PARAM_FIGURES_NUMBER, figuresNumber)
        intent.putExtra(StarLevelSettings.PARAM_FIGURE_SIZE, figureSize)
        intent.putExtra(StarLevelSettings.PARAM_FIGURE_SCALE, figureScale.toInt())
        intent.putExtra(StarLevelSettings.PARAM_ANIMATION_TIME, animationTime.toInt())
        startActivity(intent)
        finish()
    }
}