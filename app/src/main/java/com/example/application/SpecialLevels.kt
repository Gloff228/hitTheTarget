package com.example.application

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.application.level.bubblelevel.BubbleLevel
import com.example.application.level.StarLevel
import com.example.application.level.StarLevelSettings
import com.example.application.level.VegetableLevelSettingsActivity
import com.example.application.level.VegetablesLevel
import com.example.application.level.bubblelevel.BubbleSettings

class SpecialLevels : MyActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_special_levels)
    }

    fun onClickReturnButton(view: View) {
        finish()
    }

    fun onBubbleButtonButton(view: View) {
        startActivity(Intent(this, BubbleSettings::class.java))
    }

    fun onStarButtonClick(view: View) {
        startActivity(Intent(this, StarLevelSettings::class.java))
    }
    fun onVegetablesButtonClick(view: View) {
        startActivity(Intent(this, VegetableLevelSettingsActivity::class.java))
    }
}