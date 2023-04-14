package com.example.application.level

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import com.example.application.MyActivity
import com.example.application.R

class StarLevel : MyActivity() {

    private lateinit var stars: MutableList<ImageView>
    private var currentStar = 1
    private val maxCount = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_star_level)
        // Получаем ссылки на звезды
        val star1 = findViewById<ImageView>(R.id.star1)
        val star2 = findViewById<ImageView>(R.id.star2)
        val star3 = findViewById<ImageView>(R.id.star3)
        val star4 = findViewById<ImageView>(R.id.star4)
        val star5 = findViewById<ImageView>(R.id.star5)
        // Создаем список звезд
        stars = mutableListOf(star1, star2, star3, star4, star5)

        fun createAnimation(star: ImageView) {
            val anim = ScaleAnimation(
                1f, 6f, 1f, 6f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
            ).apply {
                duration = 1000
                repeatCount = 1
                setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {}
                    override fun onAnimationRepeat(animation: Animation?) {
                        star.scaleX = 6f // новый масштаб по X
                        star.scaleY = 6f // новый масштаб по Y
                        animation?.cancel()
                    }
                    override fun onAnimationEnd(animation: Animation?) {}
                })
            }
            star.startAnimation(anim)

            star.setOnClickListener {
                if (anim.hasEnded()) {
                    star.visibility = View.GONE
                    currentStar++
                    if (currentStar <= maxCount) {
                        createAnimation(stars[currentStar - 1])
                    } else {
                        finish()
                    }
                }
            }
        }

        createAnimation(stars[0])
    }
}