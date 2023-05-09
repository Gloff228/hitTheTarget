package com.example.application

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.application.utils.BackgroundColor
import com.example.application.utils.Settings
import com.example.application.utils.TargetColor
import com.example.application.utils.globalSettings


open class MyActivity : AppCompatActivity() {
    val settings: Settings = globalSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideElements()
    }

    override fun onStart() {
        super.onStart()
        setBackgroundColor(settings.backgroundColor)
    }

    fun hideElements() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    fun setBackgroundColor(color: BackgroundColor) {
        globalSettings.backgroundColor = color
        window.decorView.setBackgroundColor(color.rgb.toInt())
    }

    fun setTargetColor(color: TargetColor) {
        globalSettings.targetColor = color
    }

    fun setOutlineColor(color: TargetColor) {
        globalSettings.outlineColor = color
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        hideElements()
    }

}