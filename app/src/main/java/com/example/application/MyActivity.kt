package com.example.application

import android.content.Context
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
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader


open class MyActivity : AppCompatActivity() {
    private val globalSettingsFilename = "globalSettings"

    val settings: Settings = globalSettings

    open var NEED_HANDLE_DARK_MODE = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onResume() {
        super.onResume()
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
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
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

    fun readFromFile(filename: String): String {
        var fileContents = ""
        try {
            val inputStream: FileInputStream = openFileInput(filename)
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            fileContents = bufferedReader.readText()
            inputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return fileContents
    }

    fun writeToFile(filename: String, content: String) {
        // Warning! This function overrides all content in file
        try {
            val outputStream: FileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE)
            outputStream.write(content.toByteArray())
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    protected fun saveGlobalSettings() {
        val settingsToSave = mutableMapOf<String, Any>(
            "backgroundColor" to settings.backgroundColor,
            "targetColor" to settings.targetColor,
            "outlineColor" to settings.outlineColor,
        )
        val settingsJSON = toJson(settingsToSave)
        writeToFile(globalSettingsFilename, settingsJSON)
    }

    protected fun restoreGlobalSettings() {
        val restoredSettingsJSON = readFromFile(globalSettingsFilename)
        if (restoredSettingsJSON.isEmpty()) return
        val restoredSettings = fromJson(restoredSettingsJSON)

        if (restoredSettings.containsKey("backgroundColor")) {
            settings.backgroundColor =
                BackgroundColor.valueOf(restoredSettings["backgroundColor"] as String)
        }
        if (restoredSettings.containsKey("targetColor")) {
            settings.targetColor =
                TargetColor.valueOf(restoredSettings["targetColor"] as String)
        }
        if (restoredSettings.containsKey("outlineColor")) {
            settings.outlineColor =
                TargetColor.valueOf(restoredSettings["outlineColor"] as String)
        }
    }

    protected fun toJson(map: Map<String, Any>): String {
        return Gson().toJson(map)
    }

    protected fun fromJson(jsonString: String): Map<String, Any> {
        return Gson().fromJson(jsonString, Map::class.java) as Map<String, Any>
    }
}