package com.example.application

import android.view.View
import androidx.appcompat.app.AppCompatActivity

var selectedBackgroundColor = BackgroundColor.WHITE

enum class BackgroundColor(val rgb: Long) {
    WHITE(-1),
    GRAY(0xFF676767),
    PINK(0xFFFD65F7),
    RED(0xFFFD6868),
    BLUE(0xFF5975FF),
    PURPLE(0xFFC495FF)
}

open class MyActivity : AppCompatActivity() {

    fun hideElements() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    fun setBackground(color: BackgroundColor) {
        window.decorView.setBackgroundColor(color.rgb.toInt())
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        hideElements()
    }

}