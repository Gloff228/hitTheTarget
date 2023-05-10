package com.example.application

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.example.application.utils.BackgroundColor
import com.example.application.utils.Settings
import com.example.application.utils.TargetColor
import com.example.application.utils.globalSettings


open class MyActivity : AppCompatActivity() {
    val settings: Settings = globalSettings

    open var NEED_HANDLE_DARK_MODE = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideElements()
    }

    override fun onStart() {
        super.onStart()
        setBackgroundColor(settings.backgroundColor)
        handleColorMode()
    }

    fun hideElements() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    fun handleColorMode() {
        if (!NEED_HANDLE_DARK_MODE) return
        if (!settings.backgroundColor.isDark()) return

        handleDarkMode()
    }

    open fun handleDarkMode() {
        val root = findViewById<ViewGroup>(android.R.id.content).rootView as ViewGroup
        setTextColorForAll(root, Color.WHITE)  // change textColor to white
    }

    protected fun setTextColorForAll(parent: ViewGroup, color: Int) {
        /** finds all TextView and change their textColor */
        for (view in parent.children) {
            if (view is TextView) {
                view.setTextColor(color)
            } else if (view is ViewGroup) {
                setTextColorForAll(view, color)
            }
        }
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