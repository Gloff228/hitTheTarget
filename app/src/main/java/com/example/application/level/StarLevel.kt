package com.example.application.level

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import com.example.application.MyActivity
import com.example.application.R
import kotlin.random.Random

class StarLevel : MyActivity() {

    private lateinit var stars: MutableList<ImageView>
    private var currentStar = 1
    private val maxCount = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_star_level)
        // Получаем ссылки на звезды
//        val star1 = findViewById<ImageView>(R.id.star1)
//        val star2 = findViewById<ImageView>(R.id.star2)
//        val star3 = findViewById<ImageView>(R.id.star3)
//        val star4 = findViewById<ImageView>(R.id.star4)
//        val star5 = findViewById<ImageView>(R.id.star5)

        val layout = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.layout) // здесь R.id.layout - идентификатор вашего LinearLayout из XML разметки
        val random = Random(1)

        val sstars = arrayOfNulls<ImageView>(maxCount)

        for (i in 1..maxCount) { // здесь 10 - это количество звезд, которые вы хотите добавить
            val star = ImageView(this)
            star.layoutParams.width = 20
            star.layoutParams.height = 20
            star.setImageResource(R.drawable.star) // устанавливаем изображение звезды
            star.isClickable = true // делаем звезду кликабельной

            // устанавливаем случайные координаты звезды
            val x = random.nextInt(layout.width - star.width)
            val y = random.nextInt(layout.height - star.height)
            star.x = x.toFloat()
            star.y = y.toFloat()

            stars[i] = star
        }
        for (star in sstars) {
            layout.addView(star)
        }


        // Создаем список звезд
        //stars = mutableListOf(star1, star2, star3, star4, star5)

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
                    override fun onAnimationEnd(animation: Animation?) {

                    }
                })
            }
            star.startAnimation(anim)

            star.setOnClickListener {
                star.visibility = View.GONE
                currentStar++
                if (currentStar <= 5) {
                    createAnimation(stars[currentStar - 1])
                } else {
                    finish()
                }
            }
        }

        createAnimation(sstars[0]!!)
    }
}