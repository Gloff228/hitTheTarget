package com.example.application

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.RelativeLayout
import android.widget.TextView

class MainActivity : MyActivity() {
    lateinit var userMenuElement: RelativeLayout
    lateinit var userNameElement: TextView
    init {
        NEED_HANDLE_DARK_MODE = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userMenuElement = findViewById(R.id.user_menu)
        userNameElement = findViewById(R.id.user_name)

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

    fun onClickButtonUser(view: View) {
        userMenuElement.visibility = View.VISIBLE
    }
    fun onUser1ButtonClick(view: View) {
        userMenuElement.visibility = View.GONE
        userNameElement.text = "Пользователь 1"
    }
    fun onUser2ButtonClick(view: View) {
        userMenuElement.visibility = View.GONE
        userNameElement.text = "Пользователь 2"
    }

    fun onClickCloseUserMenu(view: View) {
        userMenuElement.visibility = View.GONE
    }
}