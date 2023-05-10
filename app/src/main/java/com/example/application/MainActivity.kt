package com.example.application

import android.content.Intent
import android.os.Bundle
import android.view.*

class MainActivity : MyActivity() {
    init {
        NEED_HANDLE_DARK_MODE = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        restoreGlobalSettings()
    }
    override fun onResume() {
        super.onResume()
        hideElements()
        setBackgroundColor(settings.backgroundColor)
    }

    fun onClickButtonStart(view: View) {
        startActivity(Intent(this, LevelMenu::class.java))
    }

    fun onClickButtonSettings(view: View) {
        startActivity(Intent(this, SettingsScreen::class.java))
    }

    fun onClickButtonUser(view: View) {}
}